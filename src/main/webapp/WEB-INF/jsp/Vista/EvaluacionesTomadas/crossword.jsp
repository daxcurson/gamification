<%
/*
Codigo afanado de esta pagina:
	
	http://www.dreamincode.net/forums/topic/342854-automatic-crossword-generator/
	
Como este codigo PHP sugiere, aqui vienen las definiciones leidas de la base de datos,
pero me parece que esto es un crucigrama hard-codeado. Habria que buscar una manera
de combinar esto con el generador xwords de manera tal de hacer un crucigrama en pantalla
que se genere solo...

Si no, cambiar de juegos y listo...

Abajo hay unas funciones para convertir estos arrays en Json, de ultima una consulta
Ajax leeria el json de una llamada Rest.
 mysql_connect("localhost", "root", "") or die(mysql_error()); 
 mysql_select_db("ori") or die(mysql_error()); 

$data = mysql_query("SELECT B, D FROM event WHERE E='Difficult'")
or die(mysql_error());
while ($row = mysql_fetch_assoc($data)){
$lat[] = $row['B'];
$lon[] = $row['D'];
}
*/
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">

<head>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<title>Crossword</title>

<script type="text/javascript" src="jquery.min.js"></script>
<!--<script type="text/javascript" src="//ajax.googleapis.com/ajax/libs/jquery/1.4.4/jquery.min.js"></script>-->
<script type="text/javascript" src="json2.js"></script>
<script type="text/javascript" src="crossword.js"></script>
<script type="text/javascript" src="tooltip.js"></script>

<style type="text/css">

#crossword {
    
text-align:center;

}


.crossword {
    
border-collapse:collapse;

font-family:"Courier New", Courier, monospace;
    
margin-left:auto;
    
margin-right:auto;
    
clear:both;
    
margin-top:10px;
    
margin-bottom:10px;

}


.crossword td {
    
border:1px solid black;
    
padding:0;
    
margin:0;
    
vertical-align:middle;
    
padding:0;
    
text-align:center;
    
width:30px;
    
height:30px;

}


.crossword .no-border {
    
border:none;

}


#clues {
    
margin:auto;

}


#clues td {
    
vertical-align:top;

}

</style>

<script type="text/javascript" src="crossword.js">
</script>


</head>


<body> 
    
<script type="text/javascript">
window.onload = function()
{
    
var words = <?php echo json_encode($lat); ?>;
var clues = <?php echo json_encode($lon); ?>;

// words[i] correlates to clues[i]
    
//var words = ["brooke", "james", "charles", "vyner", "hashim", "bat"];
    
//var clues = ["Man's best friend", "Likes to chase mice", "testing", "Flying mammal", "Has a trunk", "Large marsupial"];

    // Create crossword object with the words and clues
    
var cw = new Crossword(words, clues);

    // create the crossword grid (try to make it have a 1:1 width to height ratio in 10 tries)
    
var tries = 10; 
    
var grid = cw.getSquareGrid(tries);

    // report a problem with the words in the crossword
    
	if(grid == null)
	{
        
		var bad_words = cw.getBadWords();
        
		var str = [];
        
		for(var i = 0; i < bad_words.length; i++)
			{
            
				str.push(bad_words[i].word);
        
			}
        
		alert("Shoot! A grid could not be created with these words:\n" + str.join("\n"));
        
		return;
    
	}

    // turn the crossword grid into HTML
    
	var show_answers = false;
    
	document.getElementById("crossword").innerHTML = CrosswordUtils.toHtml(grid, show_answers);

    
	// make a nice legend for the clues
    
	var legend = cw.getLegend(grid);
    
	addLegendToPage(legend);

};

function addLegendToPage(groups)
	{
    
		for(var k in groups)
		{
        
			var html = [];
        
			for(var i = 0; i < groups[k].length; i++)
			{
            
				html.push("<li><strong>" + groups[k][i]['position'] + ".</strong> " + groups[k][i]['clue'] + "</li>");
 
       			}
        
			document.getElementById(k).innerHTML = html.join("\n");
    
		}

	}

</script>


        
    <div id="crossword">
    </div>

	<div id="clues">
		<div id="clues-inner">    
            <div id="across-box">
                <h5>Across</h5>
                <ol id="across">
                    
                        <li id="clue-3"><strong>2.</strong> tesitng four</li>
                    
                        <li id="clue-1"><strong>3.</strong> testing two</li>
                    
                        <li id="clue-0"><strong>5.</strong> testing one</li>
                    
                </ol>
            </div>
            <div id="down-box">        
                <h5>Down</h5>
                <ol id="down">
                    
                        <li id="clue-2"><strong>1.</strong> testing three</li>            
                    
                        <li id="clue-4"><strong>4.</strong> testing five</li>            
                    
                </ol>
            </div>
        </div>
    </div>
    <div class="clear"></div>
</div>
</body>

</html>