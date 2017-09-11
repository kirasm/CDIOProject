<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js"></script>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css"
          integrity="sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ" crossorigin="anonymous">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js"
            integrity="sha384-vBWWzlZJ8ea9aCX4pEW3rVHjgjt7zpkNpZk+02D9phzyeVkE+jo0ieGizqPLForn"
            crossorigin="anonymous"></script>

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"
          type="text/css">
    <link rel="stylesheet" href="./css/Untitled.css" type="text/css">

</head>

<body>
<nav class="navbar navbar-expand-md navbar-light bg-faded">
    <div class="container"><a class="navbar-brand" href="#" id="navBarTitle">CDIO</a>
        <div class="collapse navbar-collapse justify-content-end" id="navbarSupportedContent">
            <ul class="navbar-nav" id="navBarText">
                <li class="nav-item active"><a class="nav-link" href="index.php">Home</a></li>
                <li class="nav-item"><a class="nav-link active" href="products.html">Products</a></li>
                <li class="nav-item"><a class="nav-link active" href="contact.html">Contact</a></li>
                <li class="nav-item"><a class="nav-link active" href="about_us.html">About us</a></li>
                <?php
                if (empty($_COOKIE["username"])) {
                    echo '<li class="nav-item active"><a class="nav-link" href="login.html">Log In</a></li>';
                    echo '<li class="nav-item active"><a class="nav-link" href="signUp.html">Create User</a></li>';
                } else {
                    echo '<li class="nav-item active"><a class="nav-link" href="dashboard.html">Dashboard</a></li>';
                }
                echo ' </div>';
                ?>
            </ul>
        </div>
    </div>
</nav>
<div class="align-middle">
    <div class="col-md-12"><img src="./images/ChefCropped.jpg" style="height: auto; max-width: 100%;"
                                class="img-responsive">
        <div class="caption py-3 m-4" position="absolute">
        </div>
    </div>
    <!-- This is a comment   <div class="align-items-center w-100 h-100" style="background-image: url(&quot;Chef.jpg&quot;); height: auto" id="mainBanner"></div> -->
</div>
<div class="py-5 section text-center" id="section1">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <h1 class="text-primary">The cheapest self-monitoring system</h1>
                <p class="lead">Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget
                    dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur
                    ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu.</p>
            </div>
        </div>
    </div>
</div>
<div class="py-5  section" id="picSection1">
    <div class="container">
        <div class="row">
            <div class="col-md-6"><img src="./images/sensor-hand.jpg" class="img-fluid img-thumbnail"></div>
            <div class="col-md-6">
                <h1 class="" id="ps1Title">Easy to setup</h1>
                <p class="lead" id="ps1Text">Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo
                    ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes,
                    nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu,
                    pretium quis, sem. </p>
            </div>
        </div>
    </div>
</div>
<div class="py-5 section text-center" id="section2">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <h1 class="text-primary">100% Security Garantee</h1>
                <p class="lead">Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget
                    dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur
                    ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu.</p>
            </div>
        </div>
    </div>
</div>
<div class="py-5  section" id="picSection2">
    <div class="container">
        <div class="row">
            <div class="col-md-6">
                <h1 class="" id="ps2Title">Makes health-inspection a breese</h1>
                <p class="lead" id="ps2Text">Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo
                    ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes,
                    nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu,
                    pretium quis, sem. </p>
            </div>
            <div class="col-md-6"><img src="./images/Salad.jpg" class="img-fluid"></div>
        </div>
    </div>
</div>
<div class="bg-faded py-0 text-center" id="section3">
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-12">
                <p><br><strong>Food CDIO, Inc. &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;</strong> Anker
                    Egelundsvej 1, Lyngby, 2800 &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;<abbr
                            title="Phone">P:</abbr>(123) 456-7890 </p>
                <div class="my-3 row text-center">
                    <div class="col-4 col-md-6">
                        <a></a>
                    </div>
                    <div class="col-4 col-md-6">
                        <a></a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>

</html>