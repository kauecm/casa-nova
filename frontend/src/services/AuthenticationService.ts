import http from "api";

declare interface Credentials{
    email:string
    senha:string
}

type Token = string;




export const login = (credentials: Credentials) => 
http
    .post<{ jwt:Token }>('/authenticate', credentials)
    .then(res => res.data) 

export const storeToken = (jwt:Token) =>{
    localStorage.setItem('token', jwt);
}

export const getToken = () =>{
    return window.localStorage.getItem('token');
}

