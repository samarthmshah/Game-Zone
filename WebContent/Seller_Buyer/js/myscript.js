var $j = jQuery.noConflict();
// mtree.js
// Requires jquery.js 
// Copy the below function, add to your JS, and simply add a list <ul class=mtree> ... </ul>
$j.fn.extend({ 
        disableSelection : function() { 
                this.each(function() { 
                        this.onselectstart = function() { return false; }; 
                        this.unselectable = "on"; 
                        $(this).css('-moz-user-select', 'none'); 
                        $(this).css('-webkit-user-select', 'none'); 
                }); 
        } 
});
(function($, window, document, undefined) {

    // Only apply if mtree list exists
    if ($('ul.mtree').length) {


        // Settings
        var collapsed = true; // Start with collapsed menu (only level 1 items visible)
        var close_same_level = false; // Close elements on same level when opening new node.
        var duration = 400; // Animation duration should be tweaked according to easing.
        var listAnim = true; // Animate separate list items on open/close element (velocity.js only).
        var easing = 'easeOutQuart'; // Velocity.js only, defaults to 'swing' with jquery animation.


        // Set initial styles 
        $('.mtree ul').css({
            'overflow': 'hidden',
            'height': (collapsed) ? 0 : 'auto',
            'display': (collapsed) ? 'none' : 'block'
        });

        // Get node elements, and add classes for styling
        var node = $('.mtree li:has(ul)');
        node.each(function(index, val) {
            $(this).children(':first-child').css('cursor', 'pointer')
            $(this).addClass('mtree-node mtree-' + ((collapsed) ? 'closed' : 'open'));
            $(this).children('ul').addClass('mtree-level-' + ($(this).parentsUntil($('ul.mtree'), 'ul').length + 1));
        });

        // Set mtree-active class on list items for last opened element
        $('.mtree li > *:first-child').on('click.mtree-active', function(e) {
            if ($(this).parent().hasClass('mtree-closed')) {
                $('.mtree-active').not($(this).parent()).removeClass('mtree-active');
                $(this).parent().addClass('mtree-active');
            } else if ($(this).parent().hasClass('mtree-open')) {
                $(this).parent().removeClass('mtree-active');
            } else {
                $('.mtree-active').not($(this).parent()).removeClass('mtree-active');
                $(this).parent().toggleClass('mtree-active');
            }
        });

        $j('html').click(function() {

            if ($j('ul.mtree > li').hasClass('mtree-open')) {
                console.log('click OPEN');
                $j('ul.mtree > li').removeClass('mtree-open').addClass('mtree-closed');
                $j('ul.mtree > li').children('ul').first().slideToggle(duration);
            }
        });

        $j('ul.mtree').click(function(event) {
            event.stopPropagation();
        });

        // Set node click elements, preferably <a> but node links can be <span> also
        node.children(':first-child').on('click.mtree', function(e) {

            // element vars
            var el = $(this).parent().children('ul').first();
            var isOpen = $(this).parent().hasClass('mtree-open');

            // close other elements on same level if opening 
            if ((close_same_level || $('.csl').hasClass('active')) && !isOpen) {
                var close_items = $(this).closest('ul').children('.mtree-open').not($(this).parent()).children('ul');

                close_items.delay(100).slideToggle(duration, function() {
                    setNodeClass($(this).parent(), true);
                });
            }

            // force auto height of element so actual height can be extracted
            el.css({
                'height': 'auto'
            });


            // jQuery fallback animate element
            setNodeClass($(this).parent(), isOpen);
            el.slideToggle(duration);

            // We can't have nodes as links unfortunately
            e.preventDefault();
        });

        // Function for updating node class
        function setNodeClass(el, isOpen) {
            if (isOpen) {
                el.removeClass('mtree-open').addClass('mtree-closed');
            } else {
                el.removeClass('mtree-closed').addClass('mtree-open');
            }
        }

    }
}(jQuery, this, this.document));

function SliderHeights() {
	var SliderHeights, maxSliderHeight, navHeight;
	navHeight = $j('#mi-slider').find('nav').outerHeight();
	sliderHeights = $j('#mi-slider .category').map(function ()    { return $j(this).height(); }).get(),
    maxSliderHeight = Math.max.apply(null, sliderHeights);
	//$j('#mi-slider .category').css({height: maxSliderHeight})
	$j('#mi-slider').css({height: maxSliderHeight+navHeight})
   // return maxSliderHeight+navHeight;
}



function arrowsPosition() {
    var productsCarousel = $j(".products-carousel");
    var blogCarousel = $j(".blog-carousel");
    var brandsCarousel = $j(".brands-carousel .slides");
	
	var windowWidth = $j(window).width(); 
	var containerWidth = $j('.container').width(); 
	
	if ((windowWidth - containerWidth)<120) {
		productsCarousel.addClass('insideArrows');
        blogCarousel.addClass('insideArrows');
        brandsCarousel.addClass('insideArrows')
	} 
	else {
		productsCarousel.removeClass('insideArrows');
		blogCarousel.removeClass('insideArrows');
		brandsCarousel.removeClass('insideArrows')
	}
	
};

jQuery(function($) {
        "use strict"
        $j('.no-touch .btn-group').hover(function() {
            $j(this).find('.dropdown-menu').first().stop(true, true).delay(250).slideDown();
        }, function() {
            $j(this).find('.dropdown-menu').first().stop(true, true).delay(100).slideUp()
        });
        $j('.touch .dropdown-toggle').click(function(e) {
            e.preventDefault();
        })

        $j('.touch .btn-group').click(function() {
            if ($j(this).hasClass('opened')) {
                $j(this).removeClass('opened').find('.dropdown-menu').first().stop(true, true).delay(100).slideUp()
            } else {
                $j('.btn-group').each(function() {
                    $j(this).removeClass('opened').find('.dropdown-menu').first().stop(true, true).delay(0).slideUp(0)
                });
                $j(this).addClass('opened').find('.dropdown-menu').first().stop(true, true).delay(250).slideDown();
            }
        });
    })

/* CAROUSELS */
jQuery(function($) {
    "use strict"

    var productsCarousel = $j(".products-carousel");
    var testimonialsCarousel = $j('.testimonials-widget .slides');
    var blogCarousel = $j(".blog-carousel");
    var brandsCarousel = $j(".brands-carousel .slides");

	arrowsPosition();

	$j(window).resize(function () {
		arrowsPosition();
	})

    /* PRODUCTS CAROUSEL */
 
    var lazyload = 'ondemand';
    //if ($j('body').hasClass('touch')) { lazyload = ''}

    productsCarousel.each(function() {

        var showProduct = 2,
            showProductlg = 2,
            showProductmd = 2,
            showProductsm = 2,
            showProductxs = 1;


        var thisCarousel = $j(this);
        if (thisCarousel.hasClass('show-3')) {
            showProduct = 3;
            showProductlg = 3;
            showProductmd = 3
        }
        if (thisCarousel.hasClass('show-4')) {
            showProduct = 4;
            showProductlg = 4;
            showProductmd = 3
        }
        if (thisCarousel.hasClass('show-5')) {
            showProduct = 5;
            showProductlg = 4;
            showProductmd = 3;
            showProductsm = 2
        }

        thisCarousel.slick({
            dots: false,
            infinite: true,
            lazyLoad: lazyload,
            speed: 300,
            slidesToShow: showProduct,
            slidesToScroll: 1,
            responsive: [{
                breakpoint: 1200,
                settings: {
                    slidesToShow: showProductlg,
                    slidesToScroll: 1
                }
            }, {
                breakpoint: 992,
                settings: {
                    slidesToShow: showProductmd,
                    slidesToScroll: 1
                }
            }, {
                breakpoint: 768,
                settings: {
                    slidesToShow: showProductsm,
                    slidesToScroll: 1
                }
            }, {
                breakpoint: 480,
                settings: {
                    slidesToShow: showProductxs,
                    slidesToScroll: 1
                }
            }]
        });
    });


    /* BLOG POST CAROUSEL */


    blogCarousel.slick({
        dots: true,
        infinite: true,
        speed: 500,
        slidesToShow: 1,
        slidesToScroll: 1
    });
	
    /* BRAND CAROUSEL */

    var lazyload = 'ondemand';
    //if ($j('body').hasClass('touch')) { lazyload = 'none'}
    brandsCarousel.slick({
        dots: false,
        infinite: true,
        //lazyLoad: lazyload,
        autoplay: true,
        autoplaySpeed: 2000,
        speed: 500,
        slidesToShow: 6,
        slidesToScroll: 1,
        responsive: [{
            breakpoint: 1200,
            settings: {
                slidesToShow: 5,
                slidesToScroll: 1
            }
        }, {
            breakpoint: 992,
            settings: {
                slidesToShow: 4,
                slidesToScroll: 1
            }
        }, {
            breakpoint: 768,
            settings: {
                slidesToShow: 3,
                slidesToScroll: 1
            }
        },  {
            breakpoint: 640,
            settings: {
                slidesToShow: 2,
                slidesToScroll: 1
            }
        },
		{
            breakpoint: 480,
            settings: {
                slidesToShow: 1,
                slidesToScroll: 1
            }
        }]
    });

    /* Testimonials CAROUSEL */


    testimonialsCarousel.slick({
        dots: false,
        infinite: true,
        arrows: true,
        autoplay: true,
        autoplaySpeed: 2000,
        speed: 500,
        slidesToScroll: 1,
        slidesToShow: 1,
        touchMove: false
    });

})

/* Lazy Load */

jQuery(function($) {
    "use strict"
    $j('img.lazy').one('inview', function(event, isVisible) {
        if (!isVisible) {
            return;
        }
        var img = $j(this);
        img.load(function() {
            img.addClass('lazy-loaded')
        });
        img.attr('src', img.attr('data-lazy'));
        img.removeAttr('data-lazy');
    });
    $j('.paralax').one('inview', function(event, isVisible) {
        var divLazy = $j(this).children('.lazy');
        if (divLazy.attr('data-lazy')) {
            if (!isVisible) {
                return;
            }
            divLazy.addClass('lazy-loaded');
            divLazy.css("background-image", "url(" + divLazy.attr("data-lazy") + ")");
            divLazy.removeAttr('data-lazy');
        }
    });
});

jQuery(function($) {
    "use strict"
    var loadcontainer = $j('.facebook-widget').find(".loader-outer");
    loadcontainer.one('inview', function(event, visible) {
        if (visible == true) {
            $j.ajax({
                url: $j('.facebook-widget a').attr("href"),
                cache: false,
                success: function(data) {
                    setTimeout(function() {
                        loadcontainer.html(data)
                    }, 1000)
                }
            });
        }

    });
});

jQuery(function ($) {
    "use strict"
	
	if ($j(".chart").length > 0){
		
	$j(".chart").one('inview', function(event, isVisible) {
        if (!isVisible) {
            return;
        }
        var chart = $j(this);
		chart.easyPieChart({
			barColor: '#b09d6e',
			lineWidth: 4,
			size: 93,
			scaleColor: false,
			onStep: function(from, to, percent) {
				$j(this.el).find('.percent').text(Math.round(percent));
			}
		});
    });

		
}
});
jQuery(function($) {
    "use strict";
    if ($j('.gallery').length) {
        $('.gallery .img-popup').magnificPopup({
			type:'image',
			  gallery:{
			enabled:true
		  }});
	}

});
jQuery(function($) {
    "use strict";
    if ($j('.ajax-popup-link').length) {
        $('.ajax-popup-link').magnificPopup({
            type: 'ajax',
            midClick: true,
            removalDelay: 300,
            callbacks: {
                ajaxContentAdded: function() {
                    $j('.popup-carousel').slick({
                        dots: false,
                        infinite: true,
                        slidesToShow: 1,
                        asNavFor: '.popup-carousel-thumbs',
                        arrows: false,
                        fade: true,
                        cssEase: 'linear'
                    })
                    $j('.popup-carousel-thumbs').slick({
                        dots: false,
                        infinite: true,
                        slidesToShow: 2,
                        asNavFor: '.popup-carousel',
                        focusOnSelect: true
                    })
                }

            },

        });
    }
    $j('.product-main-image').on('click', function(e) {
        e.preventDefault();
        var next = $j('#product-images');
        if (next.length) {
            var top = next.offset().top;
            $j('body').animate({
                scrollTop: top
            });
        }
    });

    $j('.product-images li img').on('click', function(e) {
        e.preventDefault();
        var current = $j(this);
        var next = current.parent('li').next('li');
        if (next.length) {

            var top = next.offset().top;
            console.log(top);
            $j('body, html').animate({
                scrollTop: top
            });
        }
    });
});
jQuery(function($) {
    "use strict"
    $j('a.scroll-link').click(function() {
        $j('body, html').animate({
            scrollTop: $j($j.attr(this, 'href')).offset().top
        }, 500);
        return false;
    });
});
//#shopping-cart #top-nav

jQuery(function($) {
    "use strict";
 if ($j('body.off-canvas').length) {
		var mySlidebars = new $j.slidebars({
		});
		var shopcartBtn = $j('#shopping-cart-button'),
			navBtn = $j('#main-menu-button');
}  else {
		var shopcartBtn = $j('#shopping-cart'),
			navBtn = $j('#main-menu');

}
		var windowWidth = $j(window).width();
		
		if (windowWidth<1200) {
			shopcartBtn.appendTo('#fixed-bottom');
			navBtn.appendTo('#fixed-bottom');
		}

		$j(window).resize(function () {
		var windowWidth = $j(window).width(); 
		if (windowWidth<1200) {
			shopcartBtn.appendTo('#fixed-bottom');
			navBtn.appendTo('#fixed-bottom');
		}
		else {			
			shopcartBtn.appendTo('header');
			navBtn.appendTo('header');
		}
	})   

});

jQuery(function($) {
    "use strict";
    $j(window).on("scroll touchmove", function() {
        $j('#header').toggleClass('spy', $(document).scrollTop() > 0);
    });
});


jQuery(function($) {
    "use strict";
$j('.gallery .item').on("touchstart", function (e) {
    'use strict'; 
    var thisitem = $j(this); 
    if (thisitem.hasClass('onhover')) {
        return true;
    } else {
        thisitem.addClass('onhover');
        $j('.gallery .item').not(this).removeClass('onhover');
        e.preventDefault();
        return false;
    }
});
});
jQuery(function($) {
    "use strict";
	var windowHeight = $j(window).height()-80;
	$j('.dropdown-menu, .mtree-dropdown').css('max-height', windowHeight + 'px');

		$j(window).resize(function () {
			var windowHeight = $j(window).height()-80;
			$j('.dropdown-menu, .mtree-dropdown').css('max-height', windowHeight + 'px');
		})
});

$j(window).load(function() {
    $j("#loadergif").fadeOut();
	
	if ($j('#mi-slider').length) {		
		SliderHeights();
		$j('#mi-slider').catslider();
	}
		$j(window).resize(function () {
			if ($j('#mi-slider').length) {SliderHeights();}
		})

		var $jisotopPost = $j(".gallery")
    if ($jisotopPost.length) {
        $jisotopPost.isotope({
            itemSelector: ".item"
        });
        var $optionSets = $j(".option-set"),
            $optionLinks = $optionSets.find("a");
        $optionLinks.click(function () {
            var $this = $j(this);
            if ($this.hasClass("selected")) return false;
            var $optionSet = $this.parents(".option-set");
            $optionSet.find(".selected").removeClass("selected");
            $this.addClass("selected");
            var options = {},
                key = $optionSet.attr("data-option-key"),
                value = $this.attr("data-option-value");
            value = value === "false" ? false : value;
            options[key] = value;
            if (key === "layoutMode" && typeof changeLayoutMode === "function") changeLayoutMode($this, options);
            else $jisotopPost.isotope(options);
            return false
        })

    }

})