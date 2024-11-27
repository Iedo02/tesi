import {checkLogin} from './utils.js';

window.addEventListener('load', init);

document.getElementById('cittaPartenza').addEventListener('change', function () {
    const nomeCittaPartenza = this.value;
    const fermataPartenzaSelect = document.getElementById('fermataPartenza');
    fillFermate(nomeCittaPartenza, fermataPartenzaSelect);
});

document.getElementById('cittaArrivo').addEventListener('change', function () {
    const nomeCittaArrivo = this.value;
    const fermataArrivoSelect = document.getElementById('fermataArrivo');
    fillFermate(nomeCittaArrivo, fermataArrivoSelect);
});

document.getElementById('searchForm').addEventListener('submit', submit);

async function init() {
    const loggedIn = await checkLogin();
    if (loggedIn) {
        await fillCitta();
    }
}

async function fillCitta() {
    try {
        const cittaResponse = await fetch('/api/citta');

        if (cittaResponse.ok) {
            const citta = await cittaResponse.json();
            const cittaPartenzaSelect = document.getElementById('cittaPartenza');
            const cittaArrivoSelect = document.getElementById('cittaArrivo');
            citta.forEach(c => {
                const optionPartenza = document.createElement('option');
                optionPartenza.value = c.nome;
                optionPartenza.textContent = c.nome;
                cittaPartenzaSelect.appendChild(optionPartenza);

                const optionArrivo = document.createElement('option');
                optionArrivo.value = c.nome;
                optionArrivo.textContent = c.nome;
                cittaArrivoSelect.appendChild(optionArrivo);
            });

        } else if (cittaResponse.status === 404) {
            throw new Error('Nessuna città trovata!');

        } else {
            throw new Error('Si è verificato un errore di sistema, riprovare.');
        }

    } catch (error) {
        console.error(error);
    }
}

async function fillFermate(nomeCitta, fermataSelect) {
    try {
        fermataSelect.innerHTML = '<option value="" disabled selected>Seleziona una fermata...</option>';

        const cittaResponse = await fetch(`/api/citta/cerca?nome=${nomeCitta}`);

        if (cittaResponse.ok) {
            const citta = await cittaResponse.json();
            const idCitta = citta.idCitta;

            const fermateResponse = await fetch(`/api/fermata/cerca?idCitta=${idCitta}`);

            if (fermateResponse.ok) {
                const fermate = await fermateResponse.json();
                fermate.forEach(f => {
                    const option = document.createElement('option');
                    option.value = f.indirizzo;
                    option.textContent = f.indirizzo;
                    fermataSelect.appendChild(option);
                });

            } else if (fermateResponse.status === 404) {
                throw new Error('Nessuna fermata trovata per la città "' + citta.nome + '"!');

            } else {
                throw new Error('Si è verificato un errore di sistema, riprovare.');
            }

        } else if (cittaResponse.status === 404) {
            throw new Error('Nessuna città con nome "' + nomeCitta + '" trovata!');

        } else {
            throw new Error('Si è verificato un errore di sistema, riprovare.');
        }

    } catch (error) {
        console.error(error);
    }
}

async function submit(event) {
    event.preventDefault();

    const nomeCittaPartenza = document.getElementById('cittaPartenza').value;
    const nomeCittaArrivo = document.getElementById('cittaArrivo').value;
    const indirizzoFermataPartenza = document.getElementById('fermataPartenza').value;
    const indirizzoFermataArrivo = document.getElementById('fermataArrivo').value;
    const data = document.getElementById('data').value;
    const orario = document.getElementById('orario').value;

    const risultatiContainer = document.getElementById('result');
    risultatiContainer.innerHTML = '';

    const [fermataPartenza, fermataArrivo] = await getFermatePartenzaAndArrivo(nomeCittaPartenza, nomeCittaArrivo, indirizzoFermataPartenza, indirizzoFermataArrivo);
    const linee = await getLinee(fermataPartenza.idFermata, fermataArrivo.idFermata);

    if (linee !== null) {

        // se per tutte le linee non trovo una corsa che soddisfa i requisiti,
        // allora visualizzo un messaggio all'utente, se invece trovo anche
        // solo una corsa che soddisfa i requisiti, la visualizzo all'utente.
        var checkCorse = false;
        for (const l of linee) {
            const corse = await getCorseOfLinea(l.idLinea, fermataPartenza.idFermata, orario);
            if (corse !== null) {
                checkCorse = true;
                break;
            }
        }

        if (checkCorse) {
            for (const l of linee) {
                const distanza = await getDistanzaViaggio(l.idLinea, fermataPartenza.idFermata, fermataArrivo.idFermata);
                const prezzo = Math.round(distanza * l.costoPerKM * 100) / 100;
                const corse = await getCorseOfLinea(l.idLinea, fermataPartenza.idFermata, orario);

                if (corse !== null) {
                    for (const c of corse) {
                        const [orarioPartenza, orarioArrivo] = await getOrariPartenzaAndArrivo(c.idCorsa, fermataPartenza.idFermata, fermataArrivo.idFermata);
                        const separator = document.createElement('div');
                        separator.classList.add('separator');
                        risultatiContainer.appendChild(separator);

                        const lineaDiv = document.createElement('div');
                        lineaDiv.classList.add('result-container');
                        lineaDiv.innerHTML = `
                            <div class="info1">
                                <p style="text-align: left; margin: 0">Linea <strong>${l.codLinea}</strong> Corsa <strong>${c.codCorsa}</strong></p>
                                <p style="text-align: right; margin: 0;">${new Date(data).toLocaleDateString('it-IT')}</p>
                            </div>
                            <div class="result-row">
                                <div class="info2">
                                    <div>
                                        <h2 style="margin: 0"><strong>${nomeCittaPartenza}</strong></h2>
                                        <p style="margin: 0">${indirizzoFermataPartenza}</p>
                                        <p style="margin: 0">${new Date(data + 'T' + orarioPartenza).toLocaleTimeString('it-IT', {
                            hour: '2-digit',
                            minute: '2-digit'
                        })}</p>
                                    </div>
                                    <div style="text-align: center">
                                        <i class="fa-solid fa-angles-right" style="font-size: 40px"></i>
                                    </div>
                                    <div>
                                        <h2 style="margin: 0"><strong>${nomeCittaArrivo}</strong></h2>
                                        <p style="margin: 0">${indirizzoFermataArrivo}</p>
                                        <p style="margin: 0">${new Date(data + 'T' + orarioArrivo).toLocaleTimeString('it-IT', {
                            hour: '2-digit',
                            minute: '2-digit'
                        })}</p>
                                    </div>
                                </div>
                                <div class="info3">                                
                                    <div>
                                        <h2 class="price">${prezzo.toFixed(2)} €</h2>
                                    </div>
                                    <div class="pay-button">
                                        <button style="margin: 0">Acquista</button>
                                    </div>
                                </div>
                            </div>
                        `;
                        risultatiContainer.appendChild(lineaDiv);
                    }
                }
            }
        } else {
            const separator = document.createElement('div');
            separator.classList.add('separator');
            risultatiContainer.appendChild(separator);

            const lineaDiv = document.createElement('div');
            lineaDiv.classList.add('form-container');
            lineaDiv.innerHTML =
                `
                        <p>Non è stata trovata alcuna corsa che soddisfa i requisiti.</p>
                        `
            ;
            risultatiContainer.appendChild(lineaDiv);
        }
    } else {
        const separator = document.createElement('div');
        separator.classList.add('separator');
        risultatiContainer.appendChild(separator);

        const lineaDiv = document.createElement('div');
        lineaDiv.classList.add('form-container');
        lineaDiv.innerHTML =
            `
                        <p>Non è stata trovata alcuna linea che soddisfa i requisiti.</p>
                        `
        ;
        risultatiContainer.appendChild(lineaDiv);
    }
}

async function getFermatePartenzaAndArrivo(nomeCittaPartenza, nomeCittaArrivo, indirizzoFermataPartenza, indirizzoFermataArrivo) {
    try {
        const cittaPartenzaResponse = await fetch(
            `/api/citta/cerca?nome=${nomeCittaPartenza}`
        );
        const cittaArrivoResponse = await fetch(
            `/api/citta/cerca?nome=${nomeCittaArrivo}`
        );

        if (cittaPartenzaResponse.ok && cittaArrivoResponse.ok) {
            const cittaPartenza = await cittaPartenzaResponse.json();
            const cittaArrivo = await cittaArrivoResponse.json();
            const idCittaPartenza = cittaPartenza.idCitta;
            const idCittaArrivo = cittaArrivo.idCitta;

            const fermataPartenzaResponse = await fetch(
                `/api/fermata/cerca/filtri?indirizzo=${indirizzoFermataPartenza}&idCitta=${idCittaPartenza}`
            );
            const fermataArrivoResponse = await fetch(
                `/api/fermata/cerca/filtri?indirizzo=${indirizzoFermataArrivo}&idCitta=${idCittaArrivo}`
            );

            if (fermataPartenzaResponse.ok && fermataArrivoResponse.ok) {
                const fermataPartenza = await fermataPartenzaResponse.json();
                const fermataArrivo = await fermataArrivoResponse.json();

                return [fermataPartenza, fermataArrivo];

            } else if (fermataPartenzaResponse.status === 404) {
                throw new Error('Fermata di Partenza non trovata!');

            } else if (fermataArrivoResponse.status === 404) {
                throw new Error('Fermata di Arrivo non trovata!');

            } else {
                throw new Error('Si è verificato un errore di sistema, riprovare.');
            }

        } else if (cittaPartenzaResponse.status === 404) {
            throw new Error('Nessuna città con nome "' + nomeCittaPartenza + '" trovata!');

        } else if (cittaArrivoResponse.status === 404) {
            throw new Error('Nessuna città con nome "' + nomeCittaArrivo + '" trovata!');

        } else {
            throw new Error('Si è verificato un errore di sistema, riprovare.');
        }

    } catch
        (error) {
        console.error(error);
    }
}

async function getLinee(idFermataPartenza, idFermataArrivo) {
    try {
        const lineeResponse = await fetch(
            `/api/linea/cerca/filtri?idFermataPartenza=${idFermataPartenza}&idFermataArrivo=${idFermataArrivo}`
        );

        if (lineeResponse.ok) {
            const linee = await lineeResponse.json();

            return linee;

        } else if (lineeResponse.status === 404) {
            throw new Error('Nessuna linea trovata!');

        } else {
            throw new Error('Si è verificato un errore di sistema, riprovare.');
        }

    } catch (error) {
        console.error(error);
        return null;
    }
}

async function getDistanzaViaggio(idLinea, idFermataPartenza, idFermataArrivo) {
    try {
        const lineaFermateResponse = await fetch(
            `/api/linea-fermata/cerca/filtri?idLinea=${idLinea}&idFermataPartenza=${idFermataPartenza}&idFermataArrivo=${idFermataArrivo}`
        );

        if (!lineaFermateResponse.ok) {
            throw new Error('Si è verificato un errore di sistema, riprovare.');
        }

        const lineaFermate = await lineaFermateResponse.json();

        //Calcolo la distanza totale
        var distanza = 0;
        lineaFermate.forEach(lf => {
            distanza = distanza + lf.distanzaFermataSuccessiva;
        });
        distanza = distanza - lineaFermate[lineaFermate.length - 1].distanzaFermataSuccessiva;

        return distanza;

    } catch (error) {
        console.error(error);
    }
}

async function getCorseOfLinea(idLinea, idFermataPartenza, orario) {
    try {
        const corseResponse = await fetch(
            `/api/corsa/cerca/filtri?idLinea=${idLinea}&idFermataPartenza=${idFermataPartenza}&orario=${orario}`
        );

        if (corseResponse.ok) {
            const corse = await corseResponse.json();

            return corse;

        } else if (corseResponse.status === 404) {
            throw new Error('Nessuna corsa trovata per la linea: ' + idLinea + '.');

        } else {
            throw new Error('Si è verificato un errore di sistema, riprovare.');
        }

    } catch (error) {
        console.error(error);
        return null;
    }
}

async function getOrariPartenzaAndArrivo(idCorsa, idFermataPartenza, idFermataArrivo) {
    try {
        const corsaFermataPartenzaResponse = await fetch(
            `/api/corsa-fermata/cerca/filtri?idCorsa=${idCorsa}&idFermata=${idFermataPartenza}`
        );
        const corsaFermataArrivoResponse = await fetch(
            `/api/corsa-fermata/cerca/filtri?idCorsa=${idCorsa}&idFermata=${idFermataArrivo}`
        );

        if (!corsaFermataPartenzaResponse.ok || !corsaFermataArrivoResponse.ok) {
            throw new Error('Si è verificato un errore di sistema, riprovare.');
        }

        const corsaFermataPartenza = await corsaFermataPartenzaResponse.json();
        const corsaFermataArrivo = await corsaFermataArrivoResponse.json();
        const orarioPartenza = corsaFermataPartenza.orario;
        const orarioArrivo = corsaFermataArrivo.orario;

        return [orarioPartenza, orarioArrivo];

    } catch (error) {
        console.error(error);
    }
}