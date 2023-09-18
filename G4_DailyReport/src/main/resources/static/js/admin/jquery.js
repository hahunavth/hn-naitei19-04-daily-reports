$(document).ready(function () {
    // When the delete button is clicked, open the confirmation modal
    $('.delete-btn').click(function () {
        var employeeId = $(this).data('department-id');
        $('#confirmDeleteButton').data('department-id', employeeId);
        $('#confirmDeleteModal').modal('show');
    });

    // When the "Delete" button inside the modal is clicked, send the DELETE request
    $('#confirmDeleteButton').click(function () {
        var employeeId = $(this).data('department-id');
        $.ajax({
            url: '/admin/departments/' + employeeId,
            type: 'DELETE',
            success: function () {
                // Reload the page after successful deletion
                location.reload();
            },
            error: function () {
                alert("Error deleting department.");
            }
        });
    });
});
