
// Add your JavaScript code here



function toggleSidebar() {

    const sidebar = document.getElementById('sidebar');

    const mainContent = document.getElementById('main-content');

    sidebar.classList.toggle('collapsed');

    mainContent.classList.toggle('expanded');

}
