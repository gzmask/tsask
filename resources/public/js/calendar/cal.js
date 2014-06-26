//var currentMonth = moment().format('YYYY-MM');
//var nextMonth    = moment().add('month', 1).format('YYYY-MM');
var thisMonth = moment().format('YYYY-MM');

ajaxEvents = [];

$.ajax({
    method: "POST",
    dataType: "json",
    url: '/calevents',
    success: function(data) {
        $.each(data, function(key, event) {

            var item = {"start" : moment(event.start).format('YYYY-MM-DD'), 
              "end" : moment(event.end).format('YYYY-MM-DD'), 
              "title" : event.title, "description" : event.description};
            ajaxEvents.push(item);  
        }); 
      $('#calendar_target').clndr({
        template: $('#template-calendar').html(),
        events: ajaxEvents,
        clickEvents: {
          click: function(target) {
            console.log(target);}}, 
        multiDayEvents: {
          startDate: 'start',
          endDate: 'end'},
      });
    },
    error: function(jqXHR, textStatus, errorThrown) {
        console.log(errorThrown);
    }
});


var events = [
  { start: thisMonth + '-10', 
    end: thisMonth + '-14', 
    title: 'Multi-Day Event',
    description: 'Multi-Day Event', 
    form_id: 1 },
  { start: thisMonth + '-21', 
    end: thisMonth + '-23', 
    title: 'Another Multi-Day Event',
    description: 'Another Multi-Day Event', 
    form_id: 2 }
  ];


