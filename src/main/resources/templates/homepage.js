n =  new Date();
y = n.getFullYear();
m = n.getMonth() + 1;
d = n.getDate();
document.getElementById("date").innerHTML = "Date: "+m + "/" + d + "/" + y;


// Load the Visualization API and the corechart package.
google.charts.load('current', {'packages':['corechart']});

// Set a callback to run when the Google Visualization API is loaded.
google.charts.setOnLoadCallback(drawChart);

// Callback that creates and populates a data table,
// instantiates the pie chart, passes in the data and
// draws it.
function drawChart() {

  // Create the data table.
  var data = new google.visualization.DataTable();
  data.addColumn('string', 'Topping');
  data.addColumn('number', 'Slices');
  var data = $.ajax({
    url: "data",
    dataType: "json",
    async: false,
    }).responseText;
  // data.addRows([
  //   ['Mushrooms', 3],
  //   ['Onions', 1],
  //   ['Olives', 1],
  //   ['Zucchini', 1],
  //   ['Pepperoni', 2]
  // ]);

  // Set chart options
  var options = {'title':'Temperature Measurements',
                    titleTextStyle: {
                        color: 'white',    // any HTML string color ('red', '#cc00cc')
                        fontName: 'Raleway', // i.e. 'Times New Roman'
                        fontSize: 18, // 12, 18 whatever you want (don't specify px)
                        bold: false,    // true or false
                    },
                    legendTextStyle: {
                        color: 'white',    // any HTML string color ('red', '#cc00cc')
                        fontName: 'Raleway', // i.e. 'Times New Roman'
                        fontSize: 12, // 12, 18 whatever you want (don't specify px)
                        bold: false,    // true or false
                    },
                 'width':400,
                 'corner':'100px',
                'backgroundColor': { fill:'transparent' },
                 'height':300};

  // Instantiate and draw our chart, passing in some options.
  var chart = new google.visualization.PieChart(document.getElementById('chart_div'));
  chart.draw(data, options);
}