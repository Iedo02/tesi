export function getCookie(name) {
    const value = `; ${document.cookie}`;
    const parts = value.split(`; ${name}=`);
    if (parts.length === 2)
        return parts.pop().split(';').shift();
}

export function isLoggedIn() {
    const token = getCookie('token');
    return !!token;
}

export function checkLogin() {
    if (!isLoggedIn()) {
        window.location.href = '/login';
        return false;
    }
    return true;
}

export function logout() {
    // Rimuove il cookie di autenticazione (lo fa scadere)
    document.cookie = 'token=; path=/; expires=Thu, 01 Jan 1970 00:00:00 GMT';
    window.location.href = '/login';
}