/* eslint-disable jsx-a11y/anchor-is-valid */
import './styles.css'
import { ErrorMessage, Formik, Form, Field} from 'formik';
import { useState } from 'react';
import { login, storeToken } from 'services/AuthenticationService';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';


const FormLogin: React.FC = () => {

    const navigate = useNavigate();

    const [credentials, setCredentials] = useState({ email: '', senha: '' })
    
    const handleSubmit = () => {
            try{
            login(credentials).then(response => {
             const jwt = response.jwt
             storeToken(`Bearer ${jwt}`);
             navigate("/home");
            })
            }catch(err){
                axios.isAxiosError('Deu erro no login hein')
            }            
    }


    const handleInputChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        const { name, value } = event.target
        setCredentials({
            ...credentials, [name]: value
        })
    }



    return (

        <Formik initialValues={{}} onSubmit={handleSubmit}
        >
            <div className="login-dark div-login">
                <Form >
                    <h2 className="sr-only">Casa Nova</h2>
                    <div className="illustration">
                        <i className="icon ion-ios-locked-outline">
                        </i>
                    </div>
                    <div className="form-group">
                        <Field
                            className="form-control"
                            id="email" 
                            type="text"
                            onChange={handleInputChange}
                            name="email"
                            value={credentials.email}
                            placeholder="Email" />
                        <ErrorMessage component="span" name="email" className="form-error" />
                    </div>
                    <div className="form-group">
                        <Field
                            className="form-control"
                            id="senha"
                            onChange={handleInputChange}
                            type="password"
                            name="senha"
                            value={credentials.senha}
                            placeholder="Password" />
                        <ErrorMessage component="span" name="senha" className="form-error" />
                    </div>
                    <div className="form-group form-group-btn">

                        <button className="btn btn-primary btn-block" type="submit">Login</button>
                        
                    </div>
                    <a href="#" className="forgot">Esqueceu a senha ou E-mail?</a>
                </Form>
            </div>
        </Formik>
    );
}

export default FormLogin;