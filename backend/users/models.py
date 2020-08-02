from django.db import models
from django.contrib.auth.models import AbstractUser

# Create your models here.
class User(AbstractUser):
    route_slug = models.SlugField(max_length=40)
    name = models.CharField(max_length=30, null=False, blank=True)
    phone = models.CharField(max_length=20,blank=True,null=True)
    email = models.CharField(max_length=50)
    fuel_tank_capacity = models.CharField(max_length=50)

    def __str__(self):
        return self.name
