import ChopperMAN as ch
from os import path
import pygame

class Tronco(pygame.sprite.Sprite):
    def __init__(self):
        pygame.sprite.Sprite.__init__(self)
        self.image = pygame.image.load(path.join(ch.img_dir,'Tronco3.png')).convert_alpha()
        self.image.set_colorkey(ch.BLACK)

        self.rect = self.image.get_rect()
        self.rect.centerx = 400
        self.rect.y = 0

    def update(self):
        pass