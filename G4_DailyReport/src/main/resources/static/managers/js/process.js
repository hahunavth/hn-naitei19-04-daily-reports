$(document).ready(function () {
    processInitEvent()
});


function processInitEvent() {
    let selectedRowId;
    let action;
    // Add button event
    $("#addProcess").click(function () {
        action = "add"
    })
    //Add process

        $("#processModal .btn-primary").click(function () {
            let projectProcess = {}
            const inputElements = $("#processModal input,#processModal select, #processModal textarea");
            const projectId = $(this).data("projectid")
            for (const input of inputElements) {
                const propVal = input["id"]
                projectProcess[propVal] = input.value
            }
            if (action === "add") {
            $.ajax({
                type: "POST",
                url: `http://localhost:8080/manager/projects/${projectId}/project-processes`,
                data: JSON.stringify(projectProcess),
                dataType: "json",
                contentType: "application/json",
                success: function (response) {
                    location.reload();
                    localStorage.setItem('flashMessage', 'Create complete!');
                    console.log(response)
                },
            });
            } else {
                // // TODO: cập nhật thông tin vừa sửa
            $.ajax({
                type: "PUT",
                url: "http://localhost:8080/manager/project-processes/" + selectedRowId,
                data: JSON.stringify(projectProcess),
                dataType: "json",
                contentType: "application/json",
                success: function (response) {
                    location.reload();
                    localStorage.setItem('flashMessage', 'Edit complete');
                    console.log(response)
                },
            });
            }
        })

        // TODO: chọn bản ghi bằng click
        $(".event").click(function () {
            selectedRowId = $(this).data("uuid")
            $(".event").removeClass("selected-event")
            $(this).addClass("selected-event")
        })
        //
        // // TODO: hiển thị thông tin bản ghi muốn sửa
        $("#updateProcess").click(function () {
            action = "update";
            if (!selectedRowId) alert("Please choose record to edit!")
            else {
                // fill form project
                $.ajax({
                    type: "GET",
                    url: `http://localhost:8080/manager/project-processes/` + selectedRowId,
                    success: function (process) {
                        //TODO: lấy dữ liệu project ở database
                        const inputElements = $("#processModal input,#processModal select, #processModal textarea");
                        for (const input of inputElements) {
                            //vẽ thông tin lên form
                            const propVal = input["id"];
                            input.value = process[propVal];
                        }
                    },
                });
            }
        })


        //
        // //hiển thị thông tin modal để xóa dữ liệu
        // $("#deleteProject").click(function () {
        //     if (!selectedRowId) {
        //         $("#deleteProjectBtn").hide()
        //         $("#deleteModal .modal-body").text("Please choose record to delete!")
        //     } else {
        //         $("#deleteModal .modal-body").text(" Do you want to delete this record?")
        //         $("#deleteProjectBtn").show()
        //     }
        // })
        //

        //
        //     $("#update-project-modal").modal("hide")
        // })
        //
        //Xóa dữ liệu
        $("#deleteModal .btn-primary").click(function () {
            $.ajax({
                type: "DELETE",
                url: "http://localhost:8080/manager/project-processes/" + selectedRowId,
                success: function (process) {
                    location.reload();
                    localStorage.setItem('flashMessage', 'Delete complete!');
                    // console.log(project)
                }
            });
        })
}