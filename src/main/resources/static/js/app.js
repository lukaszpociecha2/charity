document.addEventListener("DOMContentLoaded", function() {


  var readForm = function(){

    var donation = {};

    var cats = document.querySelectorAll('input[name="categories"]');
    var categories = [];
    for (var cat of cats) {
      if (cat.checked) {
        categories.push(cat.value)
      }
    }
    donation.categories = categories;

    var bags = document.querySelector('input[name="bags"]');
    console.log('Number of bags: ', bags.value);
    donation.bags = bags.value;

    var oragnization = document.querySelector('input[name="name"]');
    donation.name = oragnization.value;

    var donatorAddress = document.querySelector('input[name="pickupData.donatorAddress.cityAddress"]');
    donation.donatorAddress = donatorAddress.value;

    var city = document.querySelector('input[name="pickupData.donatorAddress.city"]');
    donation.city = city.value;

    var postcode = document.querySelector('input[name="pickupData.donatorAddress.postcode"]');
    donation.postcode = postcode.value;

    var phone = document.querySelector('input[name="pickupData.donatorAddress.phone"]');
    donation.phone = phone.value;

    var data = document.querySelector('input[name="pickupData.date"]');
    console.log(data.value);
    donation.data = data.value;

    var time = document.querySelector('input[name="pickupData.time"]');
    console.log(time.value);
    donation.time = time.value;

    var moreInfo = document.querySelector('textarea[name="pickupData.more_info"]');
    console.log(moreInfo.value);
    donation.moreInfo = moreInfo.value;

    return donation;
  }

  /**
   * Form Select
   */
  class FormSelect {
    constructor($el) {
      this.$el = $el;
      this.options = [...$el.children];
      this.init();
    }

    init() {
      this.createElements();
      this.addEvents();
      this.$el.parentElement.removeChild(this.$el);
    }

    createElements() {
      // Input for value
      this.valueInput = document.createElement("input");
      this.valueInput.type = "text";
      this.valueInput.name = this.$el.name;

      // Dropdown container
      this.dropdown = document.createElement("div");
      this.dropdown.classList.add("dropdown");

      // List container
      this.ul = document.createElement("ul");

      // All list options
      this.options.forEach((el, i) => {
        const li = document.createElement("li");
        li.dataset.value = el.value;
        li.innerText = el.innerText;

        if (i === 0) {
          // First clickable option
          this.current = document.createElement("div");
          this.current.innerText = el.innerText;
          this.dropdown.appendChild(this.current);
          this.valueInput.value = el.value;
          li.classList.add("selected");
        }

        this.ul.appendChild(li);
      });

      this.dropdown.appendChild(this.ul);
      this.dropdown.appendChild(this.valueInput);
      this.$el.parentElement.appendChild(this.dropdown);
    }

    addEvents() {
      this.dropdown.addEventListener("click", e => {
        const target = e.target;
        this.dropdown.classList.toggle("selecting");

        // Save new value only when clicked on li
        if (target.tagName === "LI") {
          this.valueInput.value = target.dataset.value;
          this.current.innerText = target.innerText;
        }
      });
    }
  }
  document.querySelectorAll(".form-group--dropdown select").forEach(el => {
    new FormSelect(el);
  });


  var checkboxes = document.querySelectorAll('form .form-group--checkbox .checkbox');

  (function () {
    for (let checkbox of checkboxes){
      checkbox.addEventListener("click", function (evt) {
        console.log(evt.target.previousElementSibling);
        console.log(evt.target.parentElement);
        //TODO zrobic tak zeby sie podswietlało jak jest checked, bo przy form:checkbox nie działa, a przy input type="checkbox" tak
      })
    }
  })();

  /**
   * Hide elements when clicked on document
   */
  document.addEventListener("click", function(e) {
    const target = e.target;
    const tagName = target.tagName;

    if (target.classList.contains("dropdown")) return false;

    if (tagName === "LI" && target.parentElement.parentElement.classList.contains("dropdown")) {
      return false;
    }

    if (tagName === "DIV" && target.parentElement.classList.contains("dropdown")) {
      return false;
    }

    document.querySelectorAll(".form-group--dropdown .dropdown").forEach(el => {
      el.classList.remove("selecting");
    });
  });

  /**
   * Switching between form steps
   */
  class FormSteps {
    constructor(form) {
      this.$form = form;
      this.$next = form.querySelectorAll(".next-step");
      this.$prev = form.querySelectorAll(".prev-step");
      this.$step = form.querySelector(".form--steps-counter span");
      this.currentStep = 1;

      this.$stepInstructions = form.querySelectorAll(".form--steps-instructions p");
      const $stepForms = form.querySelectorAll("form > div");
      this.slides = [...this.$stepInstructions, ...$stepForms];

      this.init();
    }

    /**
     * Init all methods
     */
    init() {
      this.events();
      this.updateForm();
    }

    /**
     * All events that are happening in form
     */
    events() {
      // Next step
      this.$next.forEach(btn => {
        btn.addEventListener("click", e => {
          e.preventDefault();
          this.currentStep++;
          this.updateForm();
        });
      });

      // Previous step
      this.$prev.forEach(btn => {
        btn.addEventListener("click", e => {
          e.preventDefault();
          this.currentStep--;
          this.updateForm();
        });
      });

      // Form submit




      this.$form.querySelector("form").addEventListener("submit", function (event) {
        //event.preventDefault();

        console.log(event);

        /*$.ajax({
          url : "http://localhost:8080/donate",
          method : "post",
          contentType : 'application/json',
          data : JSON.stringify(readForm())
        });*/

        //this.submit(event);




      }) /*e => this.submit(e)*!/);*/
    }


    /**
     * Update form front-end
     * Show next or previous section etc.
     */
    updateForm() {
      this.$step.innerText = this.currentStep;

      // TODO: Validation

      this.slides.forEach(slide => {
        slide.classList.remove("active");

        if (slide.dataset.step == this.currentStep) {
          slide.classList.add("active");
        }
      });

      this.$stepInstructions[0].parentElement.parentElement.hidden = this.currentStep >= 5;
      this.$step.parentElement.hidden = this.currentStep >= 5;

      // TODO: get data from inputs and show them in summary

      (function(){
          var summary = readForm();

          var summaryPoints = document.querySelectorAll('.summary ul li');
          console.log(summaryPoints);
          summaryPoints[0].innerText=summary.categories.join(' ');
          summaryPoints[1].innerText=summary.name;
          summaryPoints[2].innerText=summary.donatorAddress;
          summaryPoints[3].innerText=summary.city;
          summaryPoints[4].innerText=summary.postcode;
          summaryPoints[5].innerText=summary.phone;
          summaryPoints[6].innerText=summary.data;
          summaryPoints[7].innerText=summary.time;
          summaryPoints[8].innerText=summary.moreInfo;


          for (let point of summaryPoints){
            console.log(point.innerText);
          }

      })()

    }

  }
  const form = document.querySelector(".form--steps");
  if (form !== null) {
    new FormSteps(form);
  }
});
