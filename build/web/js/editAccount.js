/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function demo() {
    const retVal = confirm("Are you sure?");
    if (retVal){
        document.getElementById("confirm").setAttribute("value","confirm");
    }
}

