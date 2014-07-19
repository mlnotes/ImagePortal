function Mlnotes() {
    var header = ".header";
    var footer = ".footer";
    var main = ".main";
    var mainLeft = ".main-left";

    this.init = function() {
        $(document).ready(function() {
            $(window).resize(windowResized);
            setSize();
        });
    };

    var setSize = function() {
        var height = $(window).height(),
            headerHeight = $(header).height(),
            footerHeight = $(footer).height(),
            centerHeight = height - headerHeight - footerHeight;
        $(main).height(centerHeight);
        $(mainLeft).height(centerHeight);
    };

    var windowResized = function() {
        setSize();
    };
}

var mlnotes = new Mlnotes();
mlnotes.init();