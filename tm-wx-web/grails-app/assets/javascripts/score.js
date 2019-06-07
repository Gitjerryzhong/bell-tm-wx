//= require jquery-2.2.0.min
//= require_tree .
//= require_self

if (typeof jQuery !== 'undefined') {
    (function($) {
        $(document).ajaxStart(function() {
            $('#spinner').fadeIn();
        }).ajaxStop(function() {
            $('#spinner').fadeOut();
        });
    })(jQuery);
}

$(".table").hide();
var idSelect

function showOrHide(xn, xq) {
    var id="#t";
    id = id.concat(xn, xq);
    if (idSelect != id) {
        if (idSelect) {
            $(idSelect).hide();
        }
        $(id).show();
        idSelect = id;
    }
}

