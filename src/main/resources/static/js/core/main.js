(function() {
  isWindows = navigator.platform.indexOf('Win') > -1 ? true : false;

  if (isWindows) {
    // WindowsOSのみperfectScrollbar関数を有効にする
    var ps = new PerfectScrollbar('.sidebar');
    var ps1 = new PerfectScrollbar('.sidebar-wrapper');
    var ps2 = new PerfectScrollbar('.main-panel');
    $('html').addClass('perfect-scrollbar-on');

  } else {
    $('html').addClass('perfect-scrollbar-off');
  }
})();

transparent = true;
transparentDemo = true;
fixedTop = false;

navbar_initialized = false;
backgroundOrange = false;
sidebar_mini_active = false;
toggle_initialized = false;

seq = 0, delays = 80, durations = 500;
seq2 = 0, delays2 = 80, durations2 = 500;

$(document).ready(function() {

  if ($('.full-screen-map').length == 0 && $('.bd-docs').length == 0) {
    //navbar-collapseをクリックすると、メニューが白い半透明になる
    $('.collapse').on('show.bs.collapse', function() {
      $(this).closest('.navbar').removeClass('navbar-transparent').addClass('bg-white');
    }).on('hide.bs.collapse', function() {
      $(this).closest('.navbar').addClass('navbar-transparent').removeClass('bg-white');
    });
  }

  paperDashboard.initMinimizeSidebar();

  $navbar = $('.navbar[color-on-scroll]');
  scroll_distance = $navbar.attr('color-on-scroll') || 500;

  //クラス "navbar-color-on-scroll" があるかどうかを確認し、クラス "navbar-transparent"を削除する関数を追加して、プレーンカラーに変換する
  if ($('.navbar[color-on-scroll]').length != 0) {
    paperDashboard.checkScrollForTransparentNavbar();
    $(window).on('scroll', paperDashboard.checkScrollForTransparentNavbar)
  }

  $('.form-control').on("focus", function() {
    $(this).parent('.input-group').addClass("input-group-focus");
  }).on("blur", function() {
    $(this).parent(".input-group").removeClass("input-group-focus");
  });

  //bootstrapSwitchを有効にする
  $('.bootstrap-switch').each(function() {
    $this = $(this);
    data_on_label = $this.data('on-label') || '';
    data_off_label = $this.data('off-label') || '';

    $this.bootstrapSwitch({
      onText: data_on_label,
      offText: data_off_label
    });
  });
});

$(document).on('click', '.navbar-toggle', function() {
  $toggle = $(this);

  if (paperDashboard.misc.navbar_menu_visible == 1) {
    $('html').removeClass('nav-open');
    paperDashboard.misc.navbar_menu_visible = 0;
    setTimeout(function() {
      $toggle.removeClass('toggled');
      $('#bodyClick').remove();
    }, 550);

  } else {
    setTimeout(function() {
      $toggle.addClass('toggled');
    }, 580);

    div = '<div id="bodyClick"></div>';
    $(div).appendTo('body').click(function() {
      $('html').removeClass('nav-open');
      paperDashboard.misc.navbar_menu_visible = 0;
      setTimeout(function() {
        $toggle.removeClass('toggled');
        $('#bodyClick').remove();
      }, 550);
    });

    $('html').addClass('nav-open');
    paperDashboard.misc.navbar_menu_visible = 1;
  }
});

$(window).resize(function() {
  //チャート描画アニメーションのシーケンスをリセットする
  seq = seq2 = 0;

  if ($('.full-screen-map').length == 0 && $('.bd-docs').length == 0) {
    $navbar = $('.navbar');
    isExpanded = $('.navbar').find('[data-toggle="collapse"]').attr("aria-expanded");
    if ($navbar.hasClass('bg-white') && $(window).width() > 991) {
      $navbar.removeClass('bg-white').addClass('navbar-transparent');
    } else if ($navbar.hasClass('navbar-transparent') && $(window).width() < 991 && isExpanded != "false") {
      $navbar.addClass('bg-white').removeClass('navbar-transparent');
    }
  }
});

paperDashboard = {
  misc: {
    navbar_menu_visible: 0
  },

  initMinimizeSidebar: function() {
    if ($('.sidebar-mini').length != 0) {
      sidebar_mini_active = true;
    }

    $('#minimizeSidebar').click(function() {
      var $btn = $(this);

      if (sidebar_mini_active == true) {
        $('body').addClass('sidebar-mini');
        sidebar_mini_active = true;
        paperDashboard.showSidebarMessage('Sidebar mini activated...');
      } else {
        $('body').removeClass('sidebar-mini');
        sidebar_mini_active = false;
        paperDashboard.showSidebarMessage('Sidebar mini deactivated...');
      }

      //ウィンドウのサイズ変更をシミュレートして、チャートがリアルタイムで更新されるようにする。
      var simulateWindowResize = setInterval(function() {
        window.dispatchEvent(new Event('resize'));
      }, 180);

      //アニメーションが完了した後、ウィンドウのサイズ変更のシミュレーションを停止する
      setTimeout(function() {
        clearInterval(simulateWindowResize);
      }, 1000);
    });
  },

  showSidebarMessage: function(message) {
    try {
      $.notify({
        icon: "now-ui-icons ui-1_bell-53",
        message: message
      }, {
        type: 'info',
        timer: 4000,
        placement: {
          from: 'top',
          align: 'right'
        }
      });
    } catch (e) {
      console.log('通知ライブラリがありません。通知ライブラリが追加されていることを確認してください。');
    }

  }

};

function hexToRGB(hex, alpha) {
  var r = parseInt(hex.slice(1, 3), 16),
    g = parseInt(hex.slice(3, 5), 16),
    b = parseInt(hex.slice(5, 7), 16);

  if (alpha) {
    return "rgba(" + r + ", " + g + ", " + b + ", " + alpha + ")";
  } else {
    return "rgb(" + r + ", " + g + ", " + b + ")";
  }
}