$.fn.randomize = function(selector)
{
	var $elems = selector ? $(this).find(selector) : $(this).children();
	for (var i = $elems.length; i >= 0; i--) {
		$(this).append($elems[Math.random() * i | 0]);
	}

	return this;
}
$(document).ready(function()
{
	$('#pregunta-${pregunta.id}').sortable({
		animation: 150,
		handle:".glyphicon-move",
		group: "pregunta-${pregunta.id}",
		store: {
			/**
			 * Get the order of elements. Called once during initialization.
			 * @param   {Sortable}  sortable
			 * @returns {Array}
			 */
			get: function (sortable) {
				var order = localStorage.getItem(sortable.options.group.name);
				return order ? order.split('|') : [];
			},

			/**
			 * Save the order of elements. Called onEnd (when the item is dropped).
			 * @param {Sortable}  sortable
			 */
			set: function (sortable) {
				var order = sortable.toArray();
				localStorage.setItem(sortable.options.group.name, order.join('|'));
			}
		}
	});
	$("#pregunta-${pregunta.id}").randomize();
});
