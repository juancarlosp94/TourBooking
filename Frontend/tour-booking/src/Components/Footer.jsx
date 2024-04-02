const Footer = () => {
  return (
    <footer>
      <section className="footerSection">
        <div className="column-1">
          <h2>TourBooking</h2>
          <p>
            <img
              src="/images/vector.svg"
              alt="icono de copy"
              className="icono"
            />
            Copyright 2023. 
          </p>

          <div className="contact-container">
            <div className="contact-item">
              <img src="/images/envelope-open.svg" alt="icono envelop-open" />
              <div className="email">
                <p>hola@tourbooking.com</p>
              </div>
            </div>
            <div className="contact-item">
              <img src="/images/phone.svg" alt="icono phone" />
              <div className="phone">
                <p>0800 TOUR BOOK</p>
              </div>
            </div>
          </div>
        </div>
        <div className="column-2">
          <h4>Destinos</h4>
          <div className="categories">
            <p>Playa</p>
            <p>Montaña</p>
            <p>Inolvidables</p>
          </div>
        </div>
        <div className="column-3">
          <p>Siguenos</p>
          <div className="social">
            <img
              src="/images/facebook-logo.svg"
              alt="icono de facebook"
              className="icono"
            />
            <img
              src="/images/instagram-logo.svg"
              alt="icono de instagram"
              className="icono"
            />
            <img
              src="/images/whatsapp-logo.svg"
              alt="icono de whatsapp"
              className="icono"
            />
            <img
              src="/images/youtube-logo.svg"
              alt="icono de tiktok"
              className="icono"
            />
          
          </div>
        </div>
      </section>
    </footer>
  );
};

export default Footer;
