var courbes = [];
var colors = [
    { strokeStyle: 'rgba(255, 0, 0, 1)', fillStyle: 'rgba(255, 0, 0, 0.2)', lineWidth: 2 },
    { strokeStyle: 'rgba(0, 255, 0, 1)', fillStyle: 'rgba(0, 255, 0, 0.2)', lineWidth: 2 },
    { strokeStyle: 'rgba(0, 0, 255, 1)', fillStyle: 'rgba(0, 0, 255, 0.2)', lineWidth: 2 }
];
var chart = new SmoothieChart();
chart.streamTo(document.getElementById("chart"), 500);
var index = -1;

function onConnect(companyName){
    if (!courbes[companyName])
    {
        courbes[companyName] = new TimeSeries();
        chart.addTimeSeries(
            courbes[companyName],
            randomColor()
        );

        var connection = new EventSource("/transactions/stream/" + companyName);
        connection.onmessage = function(response){
                var transaction = JSON.parse(response.data);
                console.log(transaction.price, transaction.company.companyName);
                courbes[companyName].append(new Date().getTime(), transaction.price);
        }
    }
}

function randomColor()
{
    ++index;
    if(index >= colors.length) index = 0;
    return colors[index];
}