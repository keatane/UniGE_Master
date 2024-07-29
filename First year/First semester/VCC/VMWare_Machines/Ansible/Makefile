# A phony target is one that is not really the name of a file
.PHONY: createfs createrole ping
# targets with arguments
ARGTARGETS := createrole
USEVAULT=0

# If the first argument is a target with arguments
ifneq ($(filter $(firstword $(MAKECMDGOALS)),$(ARGTARGETS)),)
  # use the rest as arguments
  RUN_ARGS := $(wordlist 2,$(words $(MAKECMDGOALS)),$(MAKECMDGOALS))
  # ...and turn them into do-nothing targets
  $(eval $(RUN_ARGS):;@:)
endif

play:
ifeq ($(USEVAULT),1)
	ansible-playbook --ask-vault-pass -i hosts playbook.yml
else
	ansible-playbook -i hosts playbook.yml
endif

createfs:
	test -d group_vars || mkdir group_vars
	test -d host_vars || mkdir host_vars
	test -d roles || mkdir roles
	touch requirements.txt
	touch requirements.yml
	touch playbook.yml
	touch hosts

createrole:
ifndef RUN_ARGS
	$(error Specify the role name)
endif
	ansible-galaxy role init roles/$(RUN_ARGS)

requirements:
	ansible-galaxy collection install -r requirements.yml 

ping:
	ansible -i hosts -m ping all