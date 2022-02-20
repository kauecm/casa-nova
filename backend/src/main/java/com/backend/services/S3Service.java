package com.backend.services;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.backend.services.exceptions.FileException;



@Service
public class S3Service {

	@Autowired
	private AmazonS3 s3Client;
	@Value("${s3.bucket}")
	private String bucketName;
	private Logger LOG = LoggerFactory.getLogger(S3Service.class);

	public URI uploadFile(MultipartFile multipartFile) {

		// as tres informações basicas para fazer upload via web Service
		try {
			String fileName = multipartFile.getOriginalFilename();
			InputStream is = multipartFile.getInputStream();
			String contantType = multipartFile.getContentType();
			return uploadFile(is, fileName, contantType);
		} catch (IOException e) {
			throw new FileException("Erro de IO: "+ e.getMessage());
		}

	}

	public URI uploadFile(InputStream is, String fileName, String contantType) {
		try {
			ObjectMetadata objMetadata = new ObjectMetadata();
			LOG.info("INICIANDO UPLOAD");
			s3Client.putObject(bucketName, fileName, is, objMetadata);
			LOG.info("UPLOAD FINALIZADO!!!!!!!!");

			return s3Client.getUrl(bucketName, fileName).toURI();
		} catch (URISyntaxException e) {
			throw new FileException("Erro ao converter URL em URI");
		}
	}
}
