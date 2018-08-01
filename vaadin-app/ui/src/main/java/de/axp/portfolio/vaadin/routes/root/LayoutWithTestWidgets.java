package de.axp.portfolio.vaadin.routes.root;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.VaadinIcons;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.progressbar.ProgressBar;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.textfield.TextField;

class LayoutWithTestWidgets extends VerticalLayout {

	LayoutWithTestWidgets() {
		setHeight("90%");
		setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);

		Label label = new Label("Hello World!");
		addAndAlign(label);

		Button button1 = new Button("1");
		button1.addClickListener(this::showButtonClickedMessage);
		addAndAlign(button1);

		Button button2 = new Button("2");
		button2.addClickListener(this::showButtonClickedMessage);
		addAndAlign(button2);

		Dialog dialog = new Dialog();
		dialog.add(new Label("Close me with the esc-key or an outside click"));
		dialog.setWidth("400px");
		dialog.setHeight("150px");
		Button button3 = new Button(VaadinIcons.DOLLAR.create());
		button3.addClickListener(event -> dialog.open());
		addAndAlign(button3);

		Checkbox checkbox = new Checkbox("Check me!");
		addAndAlign(checkbox);

		ComboBox<String> comboBox = new ComboBox<>("Browsers");
		comboBox.setItems("Google Hupf", "Motzilla Farefox", "Operazz", "Apple Safaro", "Microsoft Edgy");
		comboBox.addValueChangeListener(event -> {
			if (event.getSource().isEmpty()) {
				new Notification("No browser selected", 3000).open();
			} else {
				new Notification("Selected browser: " + event.getValue(), 3000).open();
			}
		});
		addAndAlign(comboBox);

		DatePicker datePicker = new DatePicker();
		datePicker.addValueChangeListener(event -> new Notification("New Date: " + event.getValue(), 3000).open());
		addAndAlign(datePicker);

		TextField titleField = new TextField();
		titleField.setLabel("Title");
		titleField.setPlaceholder("Sir");
		TextField firstNameField = new TextField();
		firstNameField.setLabel("First name");
		firstNameField.setPlaceholder("John");
		TextField lastNameField = new TextField();
		lastNameField.setLabel("Last name");
		lastNameField.setPlaceholder("Doge");

		FormLayout nameLayout = new FormLayout();
		nameLayout.add(titleField, firstNameField, lastNameField);
		nameLayout.setResponsiveSteps(new FormLayout.ResponsiveStep("0", 1), new FormLayout.ResponsiveStep("21em", 2),
				new FormLayout.ResponsiveStep("22em", 3));
		addAndAlign(nameLayout);

		ProgressBar progressBar = new ProgressBar();
		progressBar.setWidth("500px");
		progressBar.setIndeterminate(true);
		addAndAlign(progressBar);

		RadioButtonGroup<String> group = new RadioButtonGroup<>();
		group.setItems("Foo", "Bar", "Buzz");
		group.addValueChangeListener(event -> new Notification(
				String.format("Radio button group value changed from '%s' to '%s'", event.getOldValue(),
						event.getValue()), 3000).open());
		addAndAlign(group);

		TextField textField = new TextField();
		textField.setLabel("Text field label");
		textField.setPlaceholder("Wow!");
		textField.addValueChangeListener(event -> new Notification(
				String.format("Text field value changed from '%s' to '%s'", event.getOldValue(),
						event.getValue())).open());
		addAndAlign(textField);
	}

	private void addAndAlign(Component component) {
		setHorizontalComponentAlignment(FlexComponent.Alignment.CENTER, component);
		add(component);
	}

	private void showButtonClickedMessage(ClickEvent<Button> event) {
		new Notification("This notification has text content!", 3000).open();
	}
}