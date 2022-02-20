/* eslint-disable jsx-a11y/anchor-is-valid */
import './styles.css';
function Navbar() {

    function myFunction() {
        document.getElementById('navbarToggleExternalContent')?.classList.toggle("show");
    }

    return (

        <>
            <header>
                <nav className="container">
                    <div className="casa-nav-content">

                        <a href="#">
                            <p>Home</p>
                        </a>
                        <div className='casa-contact-container'>

                            <a href="#">
                                <div >
                                    <p >Lista de Presentes</p>
                                </div>
                            </a>
                            <a href="#">
                                <div>
                                    <p >Galeria de Fotos</p>
                                </div>
                            </a>
                            <a href="#">
                                <div>
                                    <p >Sobre nós</p>
                                </div>
                            </a>

                        </div>

                    </div>
                </nav>


                <nav className="navbar navbar-dark nav-dropdown-casa">
                    <div className="container-fluid div-btn-downdown">
                        <button onClick={myFunction} className="navbar-toggler btn-dropdown-casa"
                            type="button">
                            <span className="navbar-toggler-icon icon-navbar"></span>
                        </button>
                    </div>
                </nav>
            </header >

            <div className="collapse div-content-dropdown" id="navbarToggleExternalContent">
                <div className=" ">
                    <a href='#'><h6>Carrinho</h6></a>
                    <a href='#'><h6>Configurações</h6></a>
                    <a href='/'><h6>Sair</h6></a>
                </div>
            </div>
        </>
    );
}

export default Navbar;
