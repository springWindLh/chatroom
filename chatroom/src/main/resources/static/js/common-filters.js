angular.module("filter",[])
.filter('dateFilter', [ function() {
    return function(text) {
    	if(text==null||text==''){
    		text="-";
    	}else{
	    	var regular=/^(\d{4})-(\d{1,2})-(\d{1,2}) (\d{1,2}):(\d{1,2}):(\d{1,2})$/;
	    	if(!regular.test(text)){
	    		return text;
	    	}
	    	var date= new Date(Date.parse(text.replace(/-/g, "/")));
	    	var nowDate=new Date();
	    	var diffMs=nowDate-date;
	    	var oneMinuteMs=60*1000;
	    	var oneHourMs=oneMinuteMs*60;
	    	var oneDayMs=oneHourMs*24;
	    	if(diffMs<oneMinuteMs){
	    		text="刚刚";
	    	}else if(diffMs<oneHourMs){
	    		text=Math.floor(diffMs/oneMinuteMs)+"分钟前";
	    	}else if(diffMs<oneDayMs){
	    		text=Math.floor(diffMs/oneHourMs)+"小时前";
	    	}else{
	    		text=Math.floor(diffMs/oneDayMs)+"天前";
	    	}
    	}
        return text;
    };
}]).filter('lengthFilter', function() {
	return function(text,length){
		if(text.length>length){
			return text.substring(0,length)+"…";
		}else{
			return text;
		}
	};
});