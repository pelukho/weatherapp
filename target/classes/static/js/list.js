(function($, document, window){

    $(document).ready(function(){

        // Cloning main navigation for mobile menu
        $(".mobile-navigation").append($(".main-navigation .menu").clone());

        // Mobile menu toggle
        $(".menu-toggle").click(function(){
            $(".mobile-navigation").slideToggle();
        });


        $('.find-location').submit(function (e) {
            e.preventDefault();

            let city = $(this).find('input[type="text"]').val();

            if(city) {
                callAjax(`/add?city=${city.toLowerCase()}`);
                $(this).find('input[type="text"]').val('');
            }
        });

        $('.delete').on('click', function(){
            let btn = $(this);
            let id = btn.attr('data-id');

            callAjaxDelete(`/delete?id=${id}`);

            $(`#element-id-${id}`).fadeOut('fast');

            setTimeout(() => {
                $(`#element-id-${id}`).remove();
                }, 500
            );
        });
    });

    let callAjax = endPoint => {
        $.ajax({
            method: 'GET',
            url: document.location.origin + endPoint,
            dataType: "json",
            success: function (resp) {
                console.log(resp);

                if (isEmpty(resp)) {
                    alert('City not found!');
                    return;
                }

                if (resp.error) {
                    alert(resp.error);
                    return;
                }

                window.location.reload();

            },
            error: function(error) {
                console.log(error);
            }
        });
    }

    let callAjaxDelete = endPoint => {
        $.ajax({
            method: 'GET',
            url: document.location.origin + endPoint,
            dataType: "json",
            success: function (resp) {
                console.log(resp);

            },
            error: function(error) {
                console.log(error);
            }
        });
    }

    const isEmpty = (obj) => {
        for (let key in obj) {
            if (obj.hasOwnProperty(key))
                return false;
        }
        return true;
    }

})(jQuery, document, window);