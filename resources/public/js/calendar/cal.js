var currentMonth = moment().format('YYYY-MM');
var nextMonth    = moment().add('month', 1).format('YYYY-MM');

var events = [
  { date: currentMonth + '-' + '10', title: 'Persian Kitten Auction', location: 'Center for Beautiful Cats' },
  { date: currentMonth + '-' + '19', title: 'Cat Frisbee', location: 'Jefferson Park' },
  { date: currentMonth + '-' + '23', title: 'Kitten Demonstration', location: 'Center for Beautiful Cats' },
  { date: nextMonth + '-' + '07',    title: 'Small Cat Photo Session', location: 'Center for Cat Photography' }
];

$('#calendar_target').clndr({
  template: $('#template-calendar').html(),
  events: events
});

