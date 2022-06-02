
export const requirement = {
  mounted(el) {
    el.style.color = 'crimson';
    el.style.fontFamily = 'verdana';
    el.style.fontSize = '10px';
  }
}

let uppercase = /[A-Z]/
let specialChars = /[ `´§½!@#$%¤€£^¨&*()_+=[\]{};':"\\|,.<>/?~-]/
let numbers = /\d/

function isEmptyField(vnode) {
  return vnode.el.value == ""
}

function hasNumbers(vnode) {
  return numbers.test(vnode.el.value)
}

function hasSpecialChars(vnode) {
  return specialChars.test(vnode.el.value)
}

function hasUppercase(vnode) {
  return uppercase.test(vnode.el.value)
}

function hasPasswordRequirements(vnode) {
  /*
  Password requirements:
   - At least: 6 chars, 1 uppercase letter and 1 special char or number
  */
  let isValidLength = vnode.el.value.length >= 6
  return isValidLength && hasUppercase(vnode) && (hasSpecialChars(vnode) || hasNumbers(vnode))
}

let requirements = []


export const required = {
  updated(el, binding, vnode) {
    if (binding.modifiers.mark) {

    }

    if (binding.modifiers.name) {

    }

    if (binding.modifiers.password) {
      
    }

    if (binding.modifiers.notEmpty && isEmptyField(vnode)) {
      if (!requirements.includes(binding.value.notEmpty)) {
        requirements.push(binding.value.notEmpty)
      }
    }
    else {
      requirements = requirements.filter((val => val != binding.value.notEmpty))
    }

    console.log("hasNum: ", hasNumbers(vnode))
    if (binding.modifiers.noNum && hasNumbers(vnode)) {
      if (!requirements.includes(binding.value.noNum)) {
        requirements.push(binding.value.noNum)
      }
    }
    else {
      requirements = requirements.filter((val => val != binding.value.noNum))
    }

    console.log("hasSpec: ", hasSpecialChars(vnode))
    if (binding.modifiers.noSpec && hasSpecialChars(vnode)) {
      if (!requirements.includes(binding.value.noSpec)) {
        requirements.push(binding.value.noSpec)
      }
    }
    else {
      requirements = requirements.filter((val => val != binding.value.noSpec))
    }
    
    console.log("not a password: ", !hasPasswordRequirements(vnode))
    if (binding.modifiers.password && !hasPasswordRequirements(vnode)) {
      if (!requirements.includes(binding.value.password)) {
        requirements.push(binding.value.password)
      }
    }
    else {
      requirements = requirements.filter((val => val != binding.value.password))
    }
    console.log("reqs:", requirements)
    var textElem = document.getElementById(binding.arg)
    textElem.innerHTML = ""
    for (const req of requirements) {
      textElem.innerHTML += "<br>" + req
    }
  }
}




/*
Value:
  - [ {fun: t => t == "", req: "All fields must be filled out"}, // empty
      {fun: t => [A-Z].test(t)}, // Capital letter(s)
      {fun: t => [0-9].test(t)}, // Number(s)
      {fun: t => [..,-].test(t)} // Special char(s)
    ]

Arg:
  - Paragraph id <p id="foo"></p>

Modifiers:
  - mark:   Marks satisfied reqs
  - name:   Must be name
  - password: Must be password

*/