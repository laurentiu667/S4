import React, { useState } from 'react';
import PropTypes from 'prop-types';

function Pixelgame({ recupValeur }){

    const [nombre, setNombre] = useState('');
    const [isMouseDown, setIsMouseDown] = useState(false);

    const handleChange = (event) => {
        const newValue = event.target.value;
        setNombre(newValue);
        recupValeur(newValue);
    }

    const handleMouseDown = (event) => {
        setIsMouseDown(true);
        event.target.style.backgroundColor = 'red';
    }

    const handleMouseUp = () => {
        setIsMouseDown(false);
    }

    const handleMouseMove = (event) => {
        if (isMouseDown) {
            event.target.style.backgroundColor = 'red';
        }
    }

    const rows = [];
    for(let i = 0; i < nombre; i++) {
        const pixels = [];
        for(let j = 0; j < nombre; j++) {
            pixels.push(<div key={j} className="pixel" onMouseDown={handleMouseDown} onMouseUp={handleMouseUp} onMouseMove={handleMouseMove}></div>);
        }
        rows.push(<div key={i} className="row">{pixels}</div>);
    }
    const clickreset = () => {
        const pixels = document.querySelectorAll('.pixel');
        pixels.forEach(pixel => {
            pixel.style.backgroundColor = 'green';
        });
    }

    return(
        <div className="pixelgame" onMouseUp={handleMouseUp}>
            <h1>PixelGame</h1>
            <input className='input' type="text" placeholder="Combien par combien" onChange={handleChange}/>
            <p>tu as choisi : {nombre}</p>
            <div className="container-game">
                {rows}
            </div>
            <button className='reset' onClick={clickreset}>reset</button>
        </div>
    );
}

Pixelgame.propTypes = {
    recupValeur: PropTypes.func.isRequired
}

export default Pixelgame;