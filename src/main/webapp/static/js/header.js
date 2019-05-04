
$(document).ready(function () {
        UserPopUpHide();
        RulesPopUpHide();
        ComissionPopUpHide();
        
        $(".b-popup-content-comission").click(function (e) {
            $(".b-popup-comission").css("display", "none");
            $(".b-popup-content-comission").css("display", "none");
        });

        $(".b-popup-content-rules").click(function (e) {
            $(".b-popup-rules").css("display", "none");
            $(".b-popup-content-rules").css("display", "none");
        });

        $("body").click(function (e) {
            $(".b-popup-user").css("display", "none");
            $(".b-popup-content-user").css("display", "none");
        });

        $(".b-popup-user").click(function (e) {
            e.stopImmediatePropagation();
        });

        $(".b-popup-comission").click(function (e) {
            e.stopImmediatePropagation();
        });

        $(".b-popup-rules").click(function (e) {
            e.stopImmediatePropagation();
        });
    	$("#button-seller").click(function (e) {
            console.log("button seller clicked!");
    		 let seller = document.getElementsByClassName("current-user")[0];
    		 let buyer = document.getElementsByClassName("contractor-user")[0];
    		 let buyer_error = document.getElementsByClassName("contractor-user-errors")[0];
             let buyer_btn = document.getElementById("button-buyer");
             let seller_btn = document.getElementById("button-seller");
    		 seller.setAttribute('id','seller');
    		 seller.setAttribute('path','seller');
    		 seller.setAttribute('name','seller');
    		 buyer.setAttribute('id','buyer');
    		 buyer.setAttribute('path','buyer');
    		 buyer.setAttribute('name','buyer');
    		//buyer_error.setAttribute('path','buyer');
    		 seller_btn.style.background = '#3a7562';
             buyer_btn.style.background = '#4c977e';

        });
    	
    	$("#button-buyer").click(function (e) {
            console.log("button buyer clicked!");
    		 let buyer = document.getElementsByClassName("current-user")[0];
    		 let seller = document.getElementsByClassName("contractor-user")[0];
    		 let seller_error = document.getElementsByClassName("contractor-user-errors")[0];
             let seller_btn = document.getElementById("button-seller");
             let buyer_btn = document.getElementById("button-buyer");
    		 seller.setAttribute("id","seller");
    		 seller.setAttribute('path','seller');
    		 seller.setAttribute('name','seller');
    		 buyer.setAttribute('id','buyer');
    		 buyer.setAttribute('path','buyer');
    		 buyer.setAttribute('name','buyer');
    		// seller_error.setAttribute('path','seller');
    		 buyer_btn.style.background = '#3a7562';
             seller_btn.style.background = '#4c977e';
        });
    });


	
    function UserPopUpShow() {
        $(".b-popup-user").css("display", "block");
        $(".b-popup-content-user").css("display", "flex");
    }

    function UserPopUpHide() {
        $(".b-popup-user").css("display", "none");
        $(".b-popup-content-user").css("display", "none");
    }

    function OpenRules() {
        ComissionPopUpHide();
        $(".b-popup-rules").css("display", "block");
        $(".b-popup-content-rules").css("display", "flex");
    }

    function RulesPopUpHide() {
        $(".b-popup-rules").css("display", "none");
        $(".b-popup-content-rules").css("display", "none");
    }

    function OpenComission() {
        RulesPopUpHide();
        $(".b-popup-comission").css("display", "block");
        $(".b-popup-content-comission").css("display", "flex");
    }

    function ComissionPopUpHide() {
        $(".b-popup-comission").css("display", "none");
        $(".b-popup-content-comission").css("display", "none");
    }

    