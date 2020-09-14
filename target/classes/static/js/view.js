(function ($, document, window) {

    $(document).ready(function () {

        // Cloning main navigation for mobile menu
        $(".mobile-navigation").append($(".main-navigation .menu").clone());

        // Mobile menu toggle
        $(".menu-toggle").click(function () {
            $(".mobile-navigation").slideToggle();
        });

        let id = document.location.search.replace(/\D/g, '');

        callAjax(`/get-view?id=${id}`);

    });


    function getDayOfWeek(index) {
        let days = ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday'];

        return days[index];
    }

    function getIcon(iconCode) {
        return `https://www.weatherbit.io/static/img/icons/${iconCode}.png`;
    }

    let callAjax = endPoint => {
        $.ajax({
            method: 'GET',
            url: document.location.origin + endPoint,
            dataType: "json",
            success: function (resp) {
                console.log(resp);

                $('.loadingio-spinner-ripple-56qoak730om').addClass('active');

                let date = new Date(resp.data[0].valid_date);
                let dayOfTheWeek = getDayOfWeek(date.getDay());

                $('.location').text(`${resp.city_name}`);
                $('#temperature').text(`${resp.data[0].temp.toFixed(0)}`);
                $('#wind').text(`${resp.data[0].wind_spd.toFixed(0)}m/s`);
                $('.today .day').text(`${dayOfTheWeek}`);
                $('.today .forecast-icon img').attr('src', `${getIcon(resp.data[0].weather.icon)}`);

                $('.forecast').each(function (index) {
                    let date = new Date(resp.data[index].valid_date);
                    let dayOfTheWeek = getDayOfWeek(date.getDay());

                    let $this = $(this);
                    if (!$this.hasClass('today')) {
                        $this.find('.degree-current').text(`${resp.data[index].temp.toFixed(0)}`);
                        $this.find('.degree-min').text(`${resp.data[index].min_temp.toFixed(0)}`);
                        $this.find('.day').text(`${dayOfTheWeek}`);

                        $this.find('.forecast-icon img').attr('src', `${getIcon(resp.data[index].weather.icon)}`);
                    }

                    setTimeout(() => {
                        $('.loadingio-spinner-ripple-56qoak730om').removeClass('active');
                    }, 500);
                });
            },
            error: function (error) {
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

    const setCity = (city) => {
        localStorage.setItem('mainCity', city);
    }

    const getCity = () => localStorage.getItem('mainCity');

})(jQuery, document, window);