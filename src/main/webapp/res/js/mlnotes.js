function Mlnotes() {
    var header = ".header",
        footer = ".footer",
        main = ".main",
        mainLeft = ".main-left",
        menu = ".menu";

    var mainWidth = 0,
        menuWidth = 0;

    this.init = function() {
        $(document).ready(function() {
            mainWidth = $(main).width();
            menuWidth = $(menu).width();
            $(window).resize(windowResized);
            setSize();
        });
    };

    var setSize = function() {
        var height = $(window).height(),
            headerHeight = $(header).height(),
            footerHeight = $(footer).height(),
            centerHeight = $(main).height(),
            pullHeight = height - headerHeight - footerHeight;
            
         var width = $(window).width(),
             narrowWidth = width - 100;
         
        console.log(width, narrowWidth, mainWidth);
        
        if(centerHeight < pullHeight){
            $(main).height(pullHeight);
            $(mainLeft).height(pullHeight);
        }
        
        if(mainWidth > narrowWidth){
            $(main).width(narrowWidth);
            $(mainLeft).width(narrowWidth - menuWidth);
        }else{
            $(main).width(mainWidth);
            $(mainLeft).width(mainWidth - menuWidth);
        }
    };

    var windowResized = function() {
        setSize();
    };
}

var mlnotes = new Mlnotes();
mlnotes.init();