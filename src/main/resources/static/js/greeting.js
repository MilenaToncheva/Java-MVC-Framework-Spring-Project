

(()=> {
    fetch('/greetings/fetch')
.then((response) => response.json())
.then((greeting) => {
    console.log(greeting);
    $('#greeting')
        .append(`<h1 id="greeting-message" style="margin-top: 100px;text-align: center;color: #9971a0" th:text="${greeting.duration}">${greeting.duration} minutes left to Christmas. Happy Holidays!</h1>`)
})
.catch((err) => console.log(err));
})();