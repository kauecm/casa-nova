import './styles.css'
function FormLogin() {
    return (
        <div className="login-dark">
            <form method="post">
                <h2 className="sr-only">Casa Nova</h2>
                <div className="illustration"><i className="icon ion-ios-locked-outline"></i></div>
                <div className="form-group"><input className="form-control" type="email" name="email" placeholder="Email"/></div>
                <div className="form-group"><input className="form-control" type="password" name="password" placeholder="Password"/></div>
                <div className="form-group form-group-btn"><button className="btn btn-primary btn-block" type="submit">Log In</button></div><a href="#" className="forgot">Esqueceu a senha ou E-mail?</a></form>
        </div>
    );
}

export default FormLogin;