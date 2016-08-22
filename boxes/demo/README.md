# Vagrant box of demo stand

## Setup base infrastructure

```
$ vagrant up [vm1,vm2,vm3]
```

You may specify required VM to save machine resources:

### vm1

1. consul
2. registrator
3. consul-template

### vm2

1. consul
2. registrator
3. consul-template
4. zookeeper
5. mesos-master
6. mesos-agent
7. marathon

### vm3

1. zookeeper
2. mesos-master
3. mesos-agent
4. marathon
5. bamboo
6. marathon-lb

## Apps deployment

Requires **ansible version => v2.1.1.0**

```
$ ansible-playbook -i .vagrant/provisioners/ansible/inventory/vagrant_ansible_inventory play-apps.yml
```
