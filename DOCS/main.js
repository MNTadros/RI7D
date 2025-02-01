function toggleSidebar() {
    const sidebar = document.getElementById('sidebar');
    const mainContent = document.getElementById('main-content');
    sidebar.classList.toggle('collapsed');
    mainContent.classList.toggle('expanded');
}

const galleryData = [
    { src: "./imgs/gallery/pictures/1.jpg", description: "An abstract sketch of Tunip" },
    { src: "./imgs/gallery/pictures/2.jpg", description: "The reef we built to test our prototype"},
    { src: "./imgs/gallery/pictures/3.jpg", description: "Grease on da hands" },
    { src: "./imgs/gallery/pictures/4.jpg", description: "Algae on da train" },
    { src: "./imgs/gallery/pictures/5.jpg", description: "The cable for the VRM" },
    { src: "./imgs/gallery/pictures/6.jpg", description: "Our final robot" },
    { src: "./imgs/gallery/pictures/7.jpg", description: "Finally getting Phoneix Tuner working" },
    { src: "./imgs/gallery/pictures/8.jpg", description: "The signed polycarb panel from all the participants" },
    { src: "./imgs/gallery/pictures/9.jpg", description: "Flashing the Rio" },
    { src: "./imgs/gallery/pictures/10.jpg", description: "Our coral scoring prototype" },
    { src: "./imgs/gallery/pictures/11.png", description: "Theoretical eletrical wiring for our bot" },
    { src: "./imgs/gallery/pictures/12.png", description: "Our first ever point scoring prototype" },
    { src: "./imgs/gallery/pictures/13.jpg", description: "A sketch with dimensions for our bot" },
    { src: "./imgs/gallery/pictures/14.jpg", description: "A CADder in their natual habitat" },
    { src: "./imgs/gallery/pictures/15.jpg", description: "De-stressing after realizing we are missing another part" },
    { src: "./imgs/gallery/pictures/16.jpg", description: "One of our first concepts" },
    { src: "./imgs/gallery/pictures/17.png", description: "The claw we got laser cut" },
    { src: "./imgs/gallery/pictures/18.jpg", description: "Setting up Phoenix Tuner" },
    { src: "./imgs/gallery/pictures/19.jpg", description: "An epic sword battle" },
    { src: "./imgs/gallery/pictures/20.jpg", description: "Test driving" },
];

// Function to render all gallery items
function renderGallery() {
    const gallery = document.getElementById("gallery");
    gallery.innerHTML = "";

    galleryData.forEach(item => {
        const galleryItem = `
                    <div class="bg-white p-4 rounded-lg shadow-md hover:shadow-lg transition-shadow duration-300">
                        <img src="${item.src}" alt="Gallery Image" class="w-full h-48 object-cover rounded-md">
                        <p class="text-gray-600 mt-2">${item.description}</p>
                    </div>
                `;
        gallery.innerHTML += galleryItem;
    });
}

// Render the gallery when the page loads
document.addEventListener("DOMContentLoaded", () => {
    renderGallery();
});