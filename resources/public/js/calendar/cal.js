//var currentMonth = moment().format('YYYY-MM');
//var nextMonth    = moment().add('month', 1).format('YYYY-MM');
var thisMonth = moment().format('YYYY-MM');

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

$('#calendar_target').clndr({
  template: $('#template-calendar').html(),
  events: events,
  clickEvents: {
    click: function(target) { 
      console.log(target);}}, 
  multiDayEvents: {
    startDate: 'start',
    endDate: 'end'},
});

