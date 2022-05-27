
export const requirement = {
  mounted(el) {
    el.style.color = 'crimson';
    el.style.fontFamily = 'verdana';
    el.style.fontSize = '10px';
  }
}

function isEmptyField(field) {
  return field.innerText == ""
}

export const required = {
  updated(el, binding) {
    let requirements = ""
    el.style.color = 'crimson';
    if (binding.modifiers.notEmpty && isEmptyField(el)) {
      requirements += binding.value
    }
    document.getElementById(binding.arg).innerText = requirements
  }
}
