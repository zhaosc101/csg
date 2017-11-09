jQuery(function($) {
    $('[data-rel=popover]').popover({container:'body'});
    //show the dropdowns on top or bottom depending on window height and menu position
    $('#task-tab .dropdown-hover').on('mouseenter', function(e) {
        var offset = $(this).offset();
        var $w = $(window)
        if (offset.top > $w.scrollTop() + $w.innerHeight() - 100)
            $(this).addClass('dropup');
        else $(this).removeClass('dropup');
    });
    
});