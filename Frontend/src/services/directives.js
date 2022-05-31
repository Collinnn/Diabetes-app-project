
export const requirement = {
  mounted(el) {
    el.style.color = 'crimson';
    el.style.fontFamily = 'verdana';
    el.style.fontSize = '10px';
  }
}

let uppercase = /[A-Z]/
let specialChars = /[ `´§½!@#$%¤€£^¨&*()_+-=[\]{};':"\\|,.<>/?~]/
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
    console.log("elem: ", vnode)
    console.log("isEmpty: ", isEmptyField(vnode))
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
