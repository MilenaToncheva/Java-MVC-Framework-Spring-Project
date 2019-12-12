

(()=> {
    fetch('/greetings/fetch')
.then((response) => response.json())
.then((greeting) => {

    $('#greeting')
        .append(`<h1 style="margin-top:100px;text-align: center">
                        <span id="greeting-duration" style="margin-top: 100px;text-align: center;color: #9971a0" 
                                    th:text="${greeting.duration}">
                             ${greeting.duration}
                        </span >
                        <span id="greeting-message1" style="margin-top: 100px;text-align: center;color: #9971a0"
                                    th:text="${greeting.message1}">
                             ${greeting.message1}
                        </span>
                          <span id="greeting-message2" style="margin-top: 100px;text-align: center;color: #9971a0" 
                                     th:text="${greeting.message2}">
                            ${greeting.message2}
                     </span >
                    </h1>`)
    })
    .catch((err) => console.log(err));
    })();