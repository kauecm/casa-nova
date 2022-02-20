import axios from "axios";
import { useEffect } from "react";
import { Link } from "react-router-dom";
import { BASE_URL } from "utils/requests";


function Login(){
   
   useEffect(()=>{
       axios.post(`${BASE_URL}/login`,{email: 'kaue@gmail.com.br', senha:'admin'})
       .then(resp =>{
           console.log(resp);
       }).catch(error =>{
           console.log(error);
       })
   })
   
   
    return(
       <Link to="/login"/>
    );
}
export default Login;