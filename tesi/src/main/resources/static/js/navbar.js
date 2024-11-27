window.addEventListener('load', manageDropdown);
window.addEventListener('resize', manageDropdown);

window.addEventListener('resize', function () {
    const navLinks = document.getElementById('navLinks');
    if (window.innerWidth > 800) {
        navLinks.removeAttribute('style');
    }
});

document.getElementById('menuIcon').addEventListener('click', function () {
    const navLinks = document.getElementById('navLinks');
    if (navLinks.style.display === 'flex') {
        navLinks.style.display = 'none';
    } else {
        navLinks.style.display = 'flex';
    }
});

function manageDropdown() {
    const width = window.innerWidth;
    const navLinkDiv = document.querySelectorAll('.nav-link');
    if (width <= 800) {
        navLinkDiv.forEach(function (navLink) {
            const dropdownMenu = navLink.querySelector('.dropdown-menu');
            const link = navLink.querySelector('.link');
            const detail = navLink.querySelector('.detail');

            if (dropdownMenu && link && detail && !link.dataset.listenerAdded) {
                detail.style.display = 'unset';
                link.addEventListener('click', toggleDropdown);
                link.addEventListener('click', rotateDetail);
                link.dataset.listenerAdded = 'true';
            }
        });
    } else {
        navLinkDiv.forEach(function (navLink) {
            const dropdownMenu = navLink.querySelector('.dropdown-menu');
            const detail = navLink.querySelector('.detail');

            if (dropdownMenu && detail) {
                detail.removeAttribute('style');
                detail.classList.remove('fa-angle-down');
                detail.classList.add('fa-angle-right');
                dropdownMenu.removeAttribute('style');
            }

            const link = navLink.querySelector('.link');

            if (link && link.dataset.listenerAdded) {
                link.removeEventListener('click', toggleDropdown);
                link.removeEventListener('click', rotateDetail);
                link.dataset.listenerAdded = '';
            }
        });
    }
}

function toggleDropdown(event) {
    const navLink = event.currentTarget.parentElement;
    const dropdownMenu = navLink.querySelector('.dropdown-menu');

    if (dropdownMenu.style.display === 'block') {
        dropdownMenu.style.display = 'none';
        dropdownMenu.style.position = 'absolute';
    } else {
        dropdownMenu.style.display = 'block';
        dropdownMenu.style.position = 'static';
    }
}

function rotateDetail(event) {
    const navLink = event.currentTarget.parentElement;
    const dropdownMenu = navLink.querySelector('.dropdown-menu');
    const detail = event.currentTarget.querySelector('.detail');

    if (dropdownMenu.style.display === 'block') {
        detail.classList.remove('fa-angle-right');
        detail.classList.add('fa-angle-down');
    } else {
        detail.classList.remove('fa-angle-down');
        detail.classList.add('fa-angle-right');
    }
}