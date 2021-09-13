
import './styles.css';
import { ReactComponent as Logo } from './logo.svg'

function Navbar() {
  return (
    <nav className="main-bar">
      <Logo />
      <a href="home" className="logo-text">DS Delivery </a>
    </nav>
  )
}

export default Navbar;