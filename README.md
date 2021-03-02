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
</ul> <br>

<p> For the purpose of debugging and trying to make this application work properly, i have added in some functionly that will help me create junit tests and allows the creator of this application to share posts more easily </p> <br>

# Main Activity 
<p> This is where the prediction on what grade the four different types of surfaces will have </p> <br>

# Load Acitivty
<p> This acitivty creates a loading screen for the user to see and allows us to make it look more professional while we let the calculation and predictions in the background happen </p><br>

# Post Main (activity)
<p> This activity, creates an image of the reading that she enters into on the application and puts it into applcation phone gallery making easier to post to any social media platform </p><br>

# Email Main (acitivty)
<p> This activity, is used for when the there is a difference in the reading, the person using the application can send an email to us that contains all of the useful information that is provided by the open weather api website api that we are using </p> <br>

<p>Email functionly is used for further develop and testing into this application to allow to capture a whole range of readings.</p>


# To Do List
<p> This is my list of things i have to do, if I want to release this application to google play store </p> <br>
<p>Create prompts and develop more tests</p><br>
