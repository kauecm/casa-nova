/* eslint-disable jsx-a11y/anchor-is-valid */
import './styles.css'
import { ErrorMessage, Formik, Form, Field } from 'formik';
import * as yup from 'yup';

import axios from 'axios';
import { BASE_URL } from 'utils/requests';

function FormLogin() {

   
    
    const handleSubmit = (values: any) =>  {
        axios.post(`${BASE_URL}/login`, values)
        .then(resp => {
               console.log(resp);
               
            })
    }


    const validations = yup.object().shape({
        
        senha: yup.string().min(4).required()
    });
    return (
        <Formik initialValues={{}} onSubmit={handleSubmit} validationSchema={validations}
        >
            <div className="login-dark">
                <Form >
                    <h2 className="sr-only">Casa Nova</h2>
                    <div className="illustration">
                        <i className="icon ion-ios-locked-outline">

                        </i>
                    </div>
                    <div className="form-group">
                        <Field className="form-control" id="email" type="text" name="email" placeholder="Email" />
                        <ErrorMessage component="span" name="email" className="form-error"/>
                    </div>
                    <div className="form-group">
                        <Field className="form-control" id="senha" type="password" name="senha" placeholder="Password" />
                        <ErrorMessage component="span" name="senha" className="form-error"/>
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