from rest_framework import serializers
from rest_framework.validators import UniqueValidator
from users.models import *
from slugify import slugify


class RegistrationSerializer(serializers.ModelSerializer):
    password = serializers.CharField(
        write_only=True,
        min_length=8,
        error_messages={
            "blank": "Password cannot be empty.",
            "min_length": "Password must be atleast 8 characters.",
        },
        allow_blank=False,
        required=True
    )
    name = serializers.CharField(
        allow_blank=False,
        required=True,
        error_messages={
            "required": "Name field is required.",
        },)
    phone = serializers.CharField(allow_blank=False, required=True, error_messages={
        "required": "Name field is required.",
    },)

    class Meta:
        model = User
        fields = ['name', 'phone', 'password']

    def save(self):
        last_user_id = 0
        if User.objects.count() > 0:
            last_user_id = User.objects.last().id + 1

        route_slug = str(last_user_id) + ' ' + self.validated_data['name']
        route_slug = slugify(route_slug, max_length=40)

        user = User.objects.create_user(
            username=self.validated_data['phone'],
            password=self.validated_data['password'],
            phone=self.validated_data['phone'],
            route_slug=route_slug,
        )

        user.save()
        return user


class LoginSerializer(serializers.Serializer):
    phone = serializers.CharField(allow_blank=False)
    password = serializers.CharField(allow_blank=False)


class RealtimeSerializer(serializers.Serializer):
    class Meta:
        model = Realtime
        fields = ('realtime_data')
