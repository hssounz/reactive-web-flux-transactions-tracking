function onConnect(companyName){
var connection = new EventSource("/transactions/stream/" + companyName);

//    connection ??
    connection.onmessage = function(data){
        console.log(data);
    }
}