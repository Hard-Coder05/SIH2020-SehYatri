from django.db import models
from django.contrib.auth.models import AbstractUser

# Create your models here.
class User(AbstractUser):
    route_slug = models.SlugField(max_length=40)
    name = models.CharField(max_length=30, null=False, blank=False)
    phone = models.CharField(max_length=20,blank=True,null=True)
    email = models.CharField(max_length=50)

    def __str__(self):
        return self.name

class Vehicle(models.Model):
    user = models.ForeignKey(User,on_delete=models.CASCADE)
    vehicle_name = models.CharField(max_length = 100)
    fuel_tank_capacity = models.FloatField(max_length=10,default=0.0)
    mileage = models.FloatField(max_length=10)

class Realtime(models.Model):
    user = models.ForeignKey(User,on_delete=models.CASCADE)
    vehicle = models.ForeignKey(Vehicle,on_delete=models.CASCADE)
    realtime_data = models.FloatField()