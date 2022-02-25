import axios from 'axios';
import { BASE_URL } from 'utils/requests';

const http = axios.create({
  baseURL: BASE_URL
});
const token = localStorage.getItem('token');
http.defaults.headers.authorization = token;

export default http;