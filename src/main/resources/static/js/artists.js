(()=> {
    fetch('/artists/fetch')
.then((response) => response.json())
.then((artists) => {artists.forEach((artist) =>
$('#artistName-drop-down')
    .append(`<option class="text-center" th:value="${artist.name}" th:text="${artist.name}">${artist.name}</option>`));
})
.catch((err) => console.log(err));
})();
