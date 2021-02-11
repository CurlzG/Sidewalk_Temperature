# Sidewalk_Temperature
<p>The main approach for this code will be to create an application that would allow animal owner check the application and be able to what surface is safe for the animal to walk on.</p><br>
<p>The most important factor for this application is how I would evaluate the grading system and because we are dealing with real world elements, its important that I make approriate tests to be make sure that I can not except any strange outliers that would affect my grading system </p><br>
<p>The way this is going to work, is that by using the one call in openweather api, i will be able to compare the hourly to the daily and minutely to be able to allow to calculate teh average easily </p><br>
<h3> The grading system </h3>
<ul>
  <li>>60 Degrees</li>
  <p> This means till up to 60 degrees it is safe for animals to be walked on without having to worry about damaging there paws </p>
  <li>60-65 Degrees</li>
  <p> Between 60-65 the animals paws can be damaged within minutes of being on top of that surface</p>
  <li>65<</li>
    <p> This means that anything that is greater than 65 degrees will damage the animals paws within seconds and should be avoid </p>
</ul> 
