import ChopperMAN as ch
from os import path
import pygame

class HealthBar(pygame.sprite.Sprite):
    def __init__(self):
        pygame.sprite.Sprite.__init__(self)

        self.VIDA = ch.VIDA
        self.image = pygame.image.load(path.join(ch.img_dir,'health.png')).convert_alpha()
        self.image = pygame.transform.scale(self.image,(self.VIDA,20))
        self.image.set_colorkey(ch.BLACK)

        self.rect = self.image.get_rect()
        self.rect.centerx = ch.WIDTH/2
        self.rect.y = 50

        self.regen = 1

    def update(self):
        if self.VIDA >= 100:
            self.regen = -5
        self.VIDA += self.regen
        if self.VIDA < 0:
            self.VIDA = 0
        self.image = pygame.transform.scale(self.image,(self.VIDA,20))
        self.regen = 0