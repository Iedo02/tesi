if (document.getElementById('loginForm')) {
    document.getElementById('loginForm').addEventListener('submit', login);
}

if (document.getElementById('registrationForm')) {
    document.getElementById('registrationForm').addEventListener('submit', registration);
}


async function login(event) {
    try {
        event.preventDefault();

        const email = document.getElementById('email').value;
        const password = document.getElementById('password').value;


        const checkEmailResponse = await fetch(`/api/cliente?email=${email}`);
        if (checkEmailResponse.ok) {

            const loginResponse = await fetch(`/api/cliente/login`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({email, password}),
            });

            if (loginResponse.ok) {
                const data = await loginResponse.json();
                const {idCliente} = data;

                // Crea un token e lo aggiunge ai cookie del sito
                const token = btoa(JSON.stringify({idCliente}));
                document.cookie = `token=${token}; path=/;`;

                alert('Login effettuato con successo! Benvenuto ' + data.nome + '.');
                window.location.href = '/biglietteria';

            } else if (loginResponse.status === 404) {
                alert('Password errata, riprovare.');
                document.getElementById('email').value = '';
                document.getElementById('password').value = '';
                throw new Error('Password errata, riprovare.');

            } else {
                document.getElementById('email').value = '';
                document.getElementById('password').value = '';
                throw new Error('Si è verificato un errore di sistema, riprovare.');
            }

        } else if (checkEmailResponse.status === 404) {
            alert('Non esiste alcun utente con questa email');
            document.getElementById('email').value = '';
            document.getElementById('password').value = '';
            throw new Error('Non esiste alcun utente con questa email');

        } else {
            document.getElementById('email').value = '';
            document.getElementById('password').value = '';
            throw new Error('Si è verificato un errore di sistema, riprovare.');
        }

    } catch (error) {
        console.error(error);
    }
}

async function registration(event) {
    try {
        event.preventDefault();

        const nome = document.getElementById('nome').value;
        const cognome = document.getElementById('cognome').value;
        const email = document.getElementById('email').value;
        const password = document.getElementById('password').value;
        const conferma = document.getElementById('conferma').value;

        if (conferma !== password) {
            alert('Le password non coincidono!');
            document.getElementById('password').value = '';
            document.getElementById('conferma').value = '';
            throw new Error('Le password non coincidono!');
        }

        const checkEmailResponse = await fetch(`/api/cliente?email=${email}`);

        if (checkEmailResponse.status === 404) {
            const registrationResponse = await fetch(`/api/cliente/registrazione`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({nome, cognome, email, password}),
            });

            if (registrationResponse.ok) {
                alert('Registrazione effettuata con successo!');
                window.location.href = '/login';

            } else if (registrationResponse.status === 404) {
                alert('Registrazione fallita, riprovare.');
                throw new Error('Registrazione fallita, riprovare.');

            } else {
                alert('Si è verificato un errore di sistema, riprovare.');
                document.getElementById("nome").value = '';
                document.getElementById("cognome").value = '';
                document.getElementById("email").value = '';
                document.getElementById("password").value = '';
                document.getElementById("conferma").value = '';
                throw new Error('Si è verificato un errore di sistema, riprovare.');
            }

        } else if (checkEmailResponse.ok) {
            alert('La mail inserita è già in uso!');
            document.getElementById("email").value = '';
            throw new Error('La mail inserita è già in uso!');

        } else {
            alert('Si è verificato un errore di sistema, riprovare.');
            document.getElementById("nome").value = '';
            document.getElementById("cognome").value = '';
            document.getElementById("email").value = '';
            document.getElementById("password").value = '';
            document.getElementById("conferma").value = '';
            throw new Error('Si è verificato un errore di sistema, riprovare.');
        }

    } catch (error) {
        console.error(error);
    }
}