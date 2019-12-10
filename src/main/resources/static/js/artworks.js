(()=> {
    fetch('/artworks/fetch')
.then((response) => response.json())
.then((artworks) => {artworks.forEach((artwork) =>
$('#home-artworks-parent')
.append(`<div id="home-artworks" class="col-md-5" >
       <img src="${artwork.imageUrl}" class="border border-secondary" height="300px"/><br/>
       <div id="artwork-name">${artwork.name}</div>
       <a class="btn-artworks" href='/artworks/details/${artwork.id}'>Details</a>
  </div>`))
})
.catch((err) => console.log(err));
})();


//<div id="home-artworks-parent"class="row  d-flex justify-content-center">
//    <div th:each="artwork:${artworks}">
//         <div id="home-artworks" class="col-md-5" >
//              <img th:src="@{${artwork.imageUrl}}" class="border border-secondary" height="300px"/><br/>
//              <div id="artwork-name">[[${artwork.name}]]</div>
//              <a class="btn-artworks" th:href="@{/artworks/details/{id}(id=${artwork.id})}">Details</a>
//         </div>
//   </div>
//</div>
